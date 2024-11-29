package use_case.club_get_posts;

import data_access.InMemoryUserDataAccessObject;
import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.Announcement;
import entity.post.Post;
import entity.user.Club;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClubGetPostsInteractorTest {
    @Test
    void successTest() {
        // Uses an in memory database to test the use case with a club
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Add a club in the DAO that has a post
        DataStore<Post> posts = new DataStoreArrays<>();
        posts.add(new Announcement("title", "content"));
        posts.add(new Announcement("title2", "content2"));
        userRepository.saveClub(new Club("Roy", "ok@k.com", "password", null, posts));

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
