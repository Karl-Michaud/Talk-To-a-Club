package use_case.club_create_post;

import data_access.InMemoryUserDataAccessObject;
import entity.user.Club;
import entity.user.ClubFactory;
import entity.user.ClubUserFactory;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests for the club create post use case interactor.
 */
public class ClubCreatePostInteractorTest {
    static String clubName = "Test Club Name";
    static String clubEmail = "club@email.com";
    static String clubPassword = "TestClubPassword123";

    @Test
    public void successTest() {
        // Initialise the club factory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();


        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        final String postTitle = "Test Post Title";
        final String postDescription = "Test Post Description";

        // Create the input data for create post use case.
        ArrayList<ClubCreatePostInputData> inputs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ClubCreatePostInputData inputData = new ClubCreatePostInputData(clubEmail, postTitle + i,
                    postDescription + i);
            inputs.add(inputData);

        }

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary successPresenter = new ClubCreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubCreatePostOutputData outputData) {
                assertEquals(false, outputData.useCaseFailed());
                assertTrue(!outputData.getTitle().equals(postTitle)
                        && !outputData.getContents().equals(postDescription)
                        && !outputData.getTimeOfPosting().toString().isEmpty()
                        && !outputData.getDateOfPosting().toString().isEmpty());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToCreatePostView() {
                fail("Shouldn't run this.");
            }

            @Override
            public void switchToClubLoggedInView() {
                fail("Shouldn't run this.");
            }
        };

        // Execute the use case that need to be tested.
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, successPresenter);
        for (ClubCreatePostInputData inputData : inputs) {
            interactor.execute(inputData);
        }
        int sizePosts = userRepository.getClub(testClub.getEmail()).getClubPostsTitle().size();
        assertEquals(10, sizePosts);
    }

    @Test
    public void successTestSwitchCreatePostView() {
        // Initialise the club factory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();


        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary successPresenter = new ClubCreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubCreatePostOutputData outputData) {
                fail("Shouldn't run this");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToCreatePostView() {
                // This is expected since we are just switching views. This will always be true
            }

            @Override
            public void switchToClubLoggedInView() {
                fail("Shouldn't run this");
            }
        };

        // Execute the use case that need to be tested.
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, successPresenter);
        interactor.switchToCreatePostView();
    }

    @Test
    public void successTestSwitchToHomeView() {
        // Initialise the club factory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();


        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary successPresenter = new ClubCreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubCreatePostOutputData outputData) {
                fail("Shouldn't run this");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToCreatePostView() {
                fail("Shouldn't run this");
            }

            @Override
            public void switchToClubLoggedInView() {
                // This is expected since we are just switching views. This will always be true.
            }
        };

        // Execute the use case that need to be tested.
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, successPresenter);
        interactor.switchToClubLoggedInView();
    }

    @Test
    public void failureEmptyTitleField() {
        // Initialise the club factory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        final String postTitle = "";
        final String postDescription = "Test Post Description for empty title";

        // Create the input data for create post use case.
        ClubCreatePostInputData inputData = new ClubCreatePostInputData(clubEmail, postTitle, postDescription);

        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary failurePresenter = new ClubCreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubCreatePostOutputData outputData) {
                fail("Did not catch empty post title field");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Title field is empty.", errorMessage);
            }

            @Override
            public void switchToCreatePostView() {
                // This is expected since we are just switching views.
            }

            @Override
            public void switchToClubLoggedInView() {
                // This is expected since we are just switching views.
            }
        };

        // Execute the use case that need to be tested.
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureEmptyDescriptionField() {
        // Initialise the club factory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        final String postTitle = "Test Post Title";
        final String postDescription = "";

        // Create the input data for create post use case.
        ClubCreatePostInputData inputData = new ClubCreatePostInputData(clubEmail, postTitle, postDescription);

        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary failurePresenter = new ClubCreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubCreatePostOutputData outputData) {
                fail("Did not catch empty post content field");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Content field is empty.", errorMessage);
            }

            @Override
            public void switchToCreatePostView() {
                // This is expected since we are just switching views.
            }

            @Override
            public void switchToClubLoggedInView() {
                // This is expected since we are just switching views.
            }
        };

        // Execute the use case that need to be tested.
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureInvalidTextFieldsField() {
        // Initialise the club factory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        final String postTitle = "";
        final String postDescription = "";

        // Create the input data for create post use case.
        ClubCreatePostInputData inputData = new ClubCreatePostInputData(clubEmail, postTitle, postDescription);

        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary successPresenter = new ClubCreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubCreatePostOutputData outputData) {
                fail("Did not catch thee error");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Title field and Content field are empty.", errorMessage);
            }

            @Override
            public void switchToCreatePostView() {
                // This is expected since we are just switching views.
            }

            @Override
            public void switchToClubLoggedInView() {
                // This is expected since we are just switching views.
            }
        };

        // Execute the use case that need to be tested.
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureTitleTooLongField() {
        // Initialise the club factory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        String postTitle = "this is 100 characters long because I know it is and if I am not mistaken this " +
                "should be 100 charact";
        // postTitle is 200 characters long
        postTitle = postTitle + postTitle;
        final String postDescription = "fail";

        // Create the input data for create post use case.
        ClubCreatePostInputData inputData = new ClubCreatePostInputData(clubEmail, postTitle, postDescription);

        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary failurePresenter = new ClubCreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubCreatePostOutputData outputData) {
                fail("Did not catch the error");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Title field is longer than 130 characters.", errorMessage);
            }

            @Override
            public void switchToCreatePostView() {
                // This is expected since we are just switching views.
            }

            @Override
            public void switchToClubLoggedInView() {
                // This is expected since we are just switching views.
            }
        };

        // Execute the use case that need to be tested.
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureContentTooLongField() {
        // Initialise the club factory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        String postDescription = "this is 100 characters long because I know it is and if I am not mistaken this " +
                "should be 100 charact";
        // postDescription is 400 characters long
        postDescription = postDescription + postDescription + postDescription + postDescription;
        final String postTitle = "fail";

        // Create the input data for create post use case.
        ClubCreatePostInputData inputData = new ClubCreatePostInputData(clubEmail, postTitle, postDescription);

        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary failurePresenter = new ClubCreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubCreatePostOutputData outputData) {
                fail("Did not catch the error");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Content field is longer than 380 characters.", errorMessage);
            }

            @Override
            public void switchToCreatePostView() {
                // This is expected since we are just switching views.
            }

            @Override
            public void switchToClubLoggedInView() {
                // This is expected since we are just switching views.
            }
        };

        // Execute the use case that need to be tested.
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
