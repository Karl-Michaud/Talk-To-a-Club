package use_case.student_homepage.show_posts;

import data_access.InMemoryUserDataStudentAccessObject;
import entity.data_structure.DataStoreArrays;
import entity.post.Announcement;
import entity.post.AnnouncementFactory;
import entity.post.PostFactory;
import entity.user.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StudentShowPostsInteractorTest {

    @Test
    void successTest() {
        // Uses an in memory database to test the use case with a club
        InMemoryUserDataStudentAccessObject dao = new InMemoryUserDataStudentAccessObject();
        // Create a sample club and a student.
        UserFactory clubFactory = new ClubUserFactory();
        Club climbingClub = (Club) clubFactory.create("Climbing club", "utcc@utoronto.ca", "secure");

        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Fred", "frederik.brecht@mail.utoronto.ca", "password");

        // Create 3 sample posts.
        PostFactory announcementFactory = new AnnouncementFactory();
        Announcement announcement1 = (Announcement) announcementFactory.create("Climb the CN Tower",
                "self explanatory.");
        Map<String, Object> announcement1Data = new HashMap<>();
        announcement1Data.put("title", announcement1.getTitle());
        announcement1Data.put("content", announcement1.getContent());
        announcement1Data.put("likes", announcement1.numberOfLikes());
        announcement1Data.put("dislikes", announcement1.numberOfDislikes());
        announcement1Data.put("liked", announcement1.getLikes().contains(student.getEmail()));
        announcement1Data.put("disliked", announcement1.getDislikes().contains(student.getEmail()));
        announcement1Data.put("club-email", climbingClub.getEmail());
        announcement1Data.put("time", announcement1.timeOfPosting());
        announcement1Data.put("date", announcement1.dateOfPosting());
        Announcement announcement2 = (Announcement) announcementFactory.create("Saturday evening climbs", "self explanatory");
        Map<String, Object> announcement2Data = new HashMap<>();
        announcement2Data.put("title", announcement2.getTitle());
        announcement2Data.put("content", announcement2.getContent());
        announcement2Data.put("likes", announcement2.numberOfLikes());
        announcement2Data.put("dislikes", announcement2.numberOfDislikes());
        announcement2Data.put("liked", announcement2.getLikes().contains(student.getEmail()));
        announcement2Data.put("disliked", announcement2.getDislikes().contains(student.getEmail()));
        announcement2Data.put("club-email", climbingClub.getEmail());
        announcement2Data.put("time", announcement2.timeOfPosting());
        announcement2Data.put("date", announcement2.dateOfPosting());
        Announcement announcement3 = (Announcement) announcementFactory.create("Dynos 101", "Come learn how to do dynamic climbing moves!");
        Map<String, Object> announcement3Data = new HashMap<>();
        announcement3Data.put("title", announcement3.getTitle());
        announcement3Data.put("content", announcement3.getContent());
        announcement3Data.put("likes", announcement3.numberOfLikes());
        announcement3Data.put("dislikes", announcement3.numberOfDislikes());
        announcement3Data.put("liked", announcement3.getLikes().contains(student.getEmail()));
        announcement3Data.put("disliked", announcement3.getDislikes().contains(student.getEmail()));
        announcement3Data.put("club-email", climbingClub.getEmail());
        announcement3Data.put("time", announcement3.timeOfPosting());
        announcement3Data.put("date", announcement3.dateOfPosting());
        climbingClub.addClubPost(announcement1);
        climbingClub.addClubPost(announcement2);
        climbingClub.addClubPost(announcement3);

        student.joinClub(climbingClub);
        climbingClub.addClubMember(student);
        dao.saveStudent(student);
        dao.saveClub(climbingClub);

        Map<String, List<Map<String, Object>>> samplePostData = new HashMap<>();
        List<Map<String, Object>> samplePostsList = new ArrayList<>();
        samplePostsList.add(announcement1Data);
        samplePostsList.add(announcement2Data);
        samplePostsList.add(announcement3Data);
        samplePostData.put(climbingClub.getUsername(), samplePostsList);
        // prepare the inputData.
        StudentShowPostsInputData inputData = new StudentShowPostsInputData(student.getEmail());

        // Create a successPresenter that tests whether the test case is as we expect.
        StudentShowPostsOutputBoundary successPresenter = new StudentShowPostsOutputBoundary() {
            @Override
            public void preparePostContent(StudentShowPostsOutputData studentShowPostsOutputData) {
                assertEquals(studentShowPostsOutputData.getPostData(), samplePostData);
                assertEquals("frederik.brecht@mail.utoronto.ca", studentShowPostsOutputData.getCurrStudent());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected fail call.");
            }
        };
        StudentShowPostsInputBoundary interactor = new StudentShowPostsInteractor(dao, dao, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        // Uses an in memory database to test the use case with a club.
        InMemoryUserDataStudentAccessObject dao = new InMemoryUserDataStudentAccessObject();

        StudentShowPostsInputData inputData = new StudentShowPostsInputData("frederik.brecht@mail.utoronto.ca");

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentShowPostsOutputBoundary successPresenter = new StudentShowPostsOutputBoundary() {

            @Override
            public void preparePostContent(StudentShowPostsOutputData studentShowPostsOutputData) {
                assertEquals("frederik.brecht@mail.utoronto.ca", studentShowPostsOutputData.getCurrStudent());
                assertNull(studentShowPostsOutputData.getPostData());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("The account does not exist.", errorMessage);
            }
        };

        StudentShowPostsInputBoundary interactor = new StudentShowPostsInteractor(dao, dao, successPresenter);
        interactor.execute(inputData);
    }
}
