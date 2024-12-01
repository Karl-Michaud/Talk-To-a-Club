package use_case.club_get_posts;

import data_access.InMemoryUserDataAccessObject;
import entity.post.AnnouncementFactory;
import entity.user.Club;
import entity.user.ClubFactory;
import entity.user.ClubUserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClubGetPostsInteractorTest {
    @Test
    void successTest() {
        // Uses an in memory database to test the use case with a club
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();


        // Initialize Post factory
        AnnouncementFactory announcementFactory = new AnnouncementFactory();

        // Initialize club factory
        ClubFactory clubFactory = new ClubUserFactory();
        Club club = clubFactory.create("Roy", "ok@k.com", "password");
        club.addClubPost(announcementFactory.create("title", "content"));
        club.addClubPost(announcementFactory.create("title2", "content2"));
        userRepository.saveClub(club);

        ClubGetPostsInputData inputData = new ClubGetPostsInputData("ok@k.com");

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubGetPostsOutputBoundary successPresenter = new ClubGetPostsOutputBoundary() {
            @Override
            public void prepareDisplayPosts(ClubGetPostsOutputData outputData) {
                assertEquals("Success in Getting Club Posts", outputData.getMessage());
                assertEquals("title", outputData.getPostTitles().get(0));
                assertEquals("content", outputData.getPostBodies().get(0));
                assertEquals("title2", outputData.getPostTitles().get(1));
                assertEquals("content2", outputData.getPostBodies().get(1));
                assertEquals(2, outputData.getPostTitles().size());
                assertEquals(2, outputData.getPostBodies().size());
            }

            @Override
            public void prepareFailMessage(ClubGetPostsOutputData outputData) {
                fail("Unexpected fail call");
            }
        };

        ClubGetPostsInteractor interactor = new ClubGetPostsInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void failTest() {
        // Uses an in memory database to test the use case with no clubs
        ClubGetPostsDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        ClubGetPostsInputData inputData = new ClubGetPostsInputData("ok@k.com");

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubGetPostsOutputBoundary successPresenter = new ClubGetPostsOutputBoundary() {
            @Override
            public void prepareDisplayPosts(ClubGetPostsOutputData outputData) {
                fail("Unexpected success call");
            }

            @Override
            public void prepareFailMessage(ClubGetPostsOutputData outputData) {
                assertEquals("Failure Getting Club Posts: Club not Found", outputData.getMessage());
                assertNull(outputData.getPostTitles());
                assertNull(outputData.getPostBodies());
            }
        };

        ClubGetPostsInputBoundary interactor = new ClubGetPostsInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
