package use_case.club_create_post;

import data_access.InMemoryUserDataAccessObject;
import entity.user.Club;
import entity.user.ClubFactory;
import entity.user.ClubUserFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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

        final String postTitle = "Test Post Title";
        final String postDescription = "Test Post Description";

        // Create the input data for create post use case.
        ClubCreatePostInputData inputData = new ClubCreatePostInputData(clubEmail, postTitle, postDescription);

        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary successPresenter = new ClubCreatePostOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubCreatePostOutputData outputData) {
                assertEquals(outputData.getTitle(), postTitle);
                assertEquals(outputData.getContents(), postDescription);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
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

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary successPresenter = new ClubCreatePostOutputBoundary() {
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
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, successPresenter);
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

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary successPresenter = new ClubCreatePostOutputBoundary() {
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
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, successPresenter);
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

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary successPresenter = new ClubCreatePostOutputBoundary() {
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
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, successPresenter);
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

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubCreatePostOutputBoundary successPresenter = new ClubCreatePostOutputBoundary() {
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
        ClubCreatePostInputBoundary interactor = new ClubCreatePostInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
