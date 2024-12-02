package use_case.student_homepage.dislike;

import data_access.InMemoryUserDataAccessObject;
import entity.data_structure.DataStoreArrays;
import entity.post.Announcement;
import entity.post.AnnouncementFactory;
import entity.post.Post;
import entity.post.PostFactory;
import entity.user.*;
import org.junit.jupiter.api.Test;
import use_case.student_dislike.StudentDislikeInputData;
import use_case.student_dislike.StudentDislikeInteractor;
import use_case.student_dislike.StudentDislikeOutputBoundary;
import use_case.student_dislike.StudentDislikeOutputData;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StudentDislikeInteractorTest {

    @Test
    void successDislikedTest() {
        // This test shows that a dislike is properly added after a user dislikes a post.
        // Set up in-memory repository
        InMemoryUserDataAccessObject dao = new InMemoryUserDataAccessObject();

        // Create a club
        ClubFactory clubFactory = new ClubUserFactory();
        Club club = clubFactory.create("Photography Club", "photo@university.com", "password");
        club.setClubDescription("For photography enthusiasts.");

        // Create a student in the club
        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Alice", "alice@university.com", "password");
        student.joinClub(club);
        club.addClubMember(student);


        PostFactory postFactory = new AnnouncementFactory();
        // Create a post for the club
        Post post = postFactory.create("Black and White Photo contest announcement.", "We're planning to host"
                + "a photo contest around the theme black and white photos and would like to hear your feedback!" +
                " Get creative," + "ditch the colors, and win prizes!");

        club.addClubPost(post);
        dao.saveClub(club);
        dao.savePost(post, club);
        dao.saveStudent(student);
        // Input Data
        Map<String, Object> postData = new HashMap<String, Object>();
        postData.put("title", post.getTitle());
        postData.put("content", post.getContent());
        postData.put("likes", post.numberOfLikes());
        postData.put("dislikes", post.numberOfDislikes());
        postData.put("liked", post.getLikes().contains(student.getEmail()));
        postData.put("disliked", post.getDislikes().contains(student.getEmail()));
        postData.put("club-email", club.getEmail());
        postData.put("time", post.timeOfPosting());
        postData.put("date", post.dateOfPosting());

        StudentDislikeInputData inputData = new StudentDislikeInputData(student.getEmail(), postData);

        // Output boundary for success verification
        StudentDislikeOutputBoundary successPresenter = new StudentDislikeOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentDislikeOutputData data) {
                assertEquals(postData, data.getPostData());
                assertTrue((Boolean) data.getPostData().get("disliked"));
            }

            @Override
            public void prepareErrorView(String errorMessage) {
                fail("Unexpected fail call");
            }
        };
        // Interactor execution
        StudentDislikeInteractor interactor = new StudentDislikeInteractor(dao, dao, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void successUndislikedTest() {
        // This test shows whether the dislike is properly removed when a user removes their dislike from a post.
        // Set up in-memory repository
        InMemoryUserDataAccessObject dao = new InMemoryUserDataAccessObject();

        // Create a club
        ClubFactory clubFactory = new ClubUserFactory();
        Club club = clubFactory.create("Photography Club", "photo@university.com", "password");
        club.setClubDescription("For photography enthusiasts.");

        // Create a student in the club
        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Alice", "alice@university.com", "password");
        student.joinClub(club);
        club.addClubMember(student);


        PostFactory postFactory = new AnnouncementFactory();
        // Create a post for the club
        Post post = postFactory.create("Black and White Photo contest announcement.", "We're planning to host"
                + "a photo contest around the theme black and white photos and would like to hear your feedback!" +
                " Get creative," + "ditch the colors, and win prizes!");
        post.addDislike(student);
        dao.saveClub(club);
        dao.savePost(post, club);
        dao.saveStudent(student);
        // Input Data
        Map<String, Object> postData = new HashMap<String, Object>();
        postData.put("title", post.getTitle());
        postData.put("content", post.getContent());
        postData.put("likes", post.numberOfLikes());
        postData.put("dislikes", post.numberOfDislikes());
        postData.put("liked", post.getLikes().contains(student.getEmail()));
        postData.put("disliked", post.getDislikes().contains(student.getEmail()));
        postData.put("club-email", club.getEmail());
        postData.put("time", post.timeOfPosting());
        postData.put("date", post.dateOfPosting());

        StudentDislikeInputData inputData = new StudentDislikeInputData(student.getEmail(), postData);

        // Output boundary for success verification
        StudentDislikeOutputBoundary successPresenter = new StudentDislikeOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentDislikeOutputData data) {
                assertEquals(postData, data.getPostData());
                assertEquals("Photography Club", data.getClubName());
                assertFalse((Boolean) data.getPostData().get("disliked"));
            }

            @Override
            public void prepareErrorView(String errorMessage) {
                fail("Unexpected fail call");
            }
        };
        // Interactor execution
        StudentDislikeInteractor interactor = new StudentDislikeInteractor(dao, dao, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void testPostNotFound() {
        // Set up in-memory repository
        InMemoryUserDataAccessObject dao = new InMemoryUserDataAccessObject();

        // Create a club
        ClubFactory clubFactory = new ClubUserFactory();
        Club club = clubFactory.create("Photography Club", "photo@university.com", "password");
        club.setClubDescription("For photography enthusiasts.");

        // Create a student in the club
        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Alice", "alice@university.com", "password");
        student.joinClub(club);
        club.addClubMember(student);

        PostFactory postFactory = new AnnouncementFactory();
        // Create a post for the club
        Post post = postFactory.create("Black and White Photo contest announcement.", "Details about the contest...");
        club.addClubPost(post);
        dao.saveClub(club);
        dao.savePost(post, club);
        dao.saveStudent(student);

        // Input Data with incorrect post date/time
        Map<String, Object> postData = new HashMap<>();
        postData.put("title", post.getTitle());
        postData.put("content", post.getContent());
        postData.put("likes", post.numberOfLikes());
        postData.put("dislikes", post.numberOfDislikes());
        postData.put("liked", post.getLikes().contains(student.getEmail()));
        postData.put("disliked", post.getDislikes().contains(student.getEmail()));
        postData.put("club-email", club.getEmail());
        postData.put("time", "12:00:00"); // Incorrect time
        postData.put("date", "2022-01-01"); // Incorrect date

        StudentDislikeInputData inputData = new StudentDislikeInputData(student.getEmail(), postData);

        // Output boundary for error verification
        StudentDislikeOutputBoundary errorPresenter = new StudentDislikeOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentDislikeOutputData data) {
                fail("Unexpected success call");
            }

            @Override
            public void prepareErrorView(String errorMessage) {
                assertEquals("The post doesn't exist", errorMessage);
            }
        };

        // Interactor execution
        StudentDislikeInteractor interactor = new StudentDislikeInteractor(dao, dao, errorPresenter);
        interactor.execute(inputData);
    }

    @Test
    void testPostDifferentTime() {
        // Set up in-memory repository
        InMemoryUserDataAccessObject dao = new InMemoryUserDataAccessObject();

        // Create a club
        ClubFactory clubFactory = new ClubUserFactory();
        Club club = clubFactory.create("Photography Club", "photo@university.com", "password");
        club.setClubDescription("For photography enthusiasts.");

        // Create a student in the club
        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Alice", "alice@university.com", "password");
        student.joinClub(club);
        club.addClubMember(student);

        PostFactory postFactory = new AnnouncementFactory();
        // Create a post for the club
        Post post = postFactory.create("Black and White Photo contest announcement.", "Details about the contest...");
        club.addClubPost(post);
        dao.saveClub(club);
        dao.savePost(post, club);
        dao.saveStudent(student);

        // Input Data with incorrect post date/time
        Map<String, Object> postData = new HashMap<>();
        postData.put("title", post.getTitle());
        postData.put("content", post.getContent());
        postData.put("likes", post.numberOfLikes());
        postData.put("dislikes", post.numberOfDislikes());
        postData.put("liked", post.getLikes().contains(student.getEmail()));
        postData.put("disliked", post.getDislikes().contains(student.getEmail()));
        postData.put("club-email", club.getEmail());
        postData.put("time", "12:00:00"); // Incorrect time
        postData.put("date", post.dateOfPosting()); // Correct date

        StudentDislikeInputData inputData = new StudentDislikeInputData(student.getEmail(), postData);

        // Output boundary for error verification
        StudentDislikeOutputBoundary errorPresenter = new StudentDislikeOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentDislikeOutputData data) {
                fail("Unexpected success call");
            }

            @Override
            public void prepareErrorView(String errorMessage) {
                assertEquals("The post doesn't exist", errorMessage);
            }
        };

        // Interactor execution
        StudentDislikeInteractor interactor = new StudentDislikeInteractor(dao, dao, errorPresenter);
        interactor.execute(inputData);
    }


}
