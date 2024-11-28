package use_case.club_logged_in.club_create_post;

import data_access.InMemoryUserDataAccessObject;
import entity.user.Club;
import entity.user.ClubFactory;
import entity.user.ClubUserFactory;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import use_case.club_create_post.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the club create post use case interactor.
 */
public class ClubCreatePostInteractorTest {
    static String clubName = "Test Club Name";
    static String clubEmail = "club@email.com";
    static String clubPassword = "TestClubPassword123";
    static Club testClub;

    @BeforeAll
    public static void setUp() {
        // Initialise the club factory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        testClub = clubFactory.create(clubName, clubEmail, clubPassword);
    }

    @Test
    public void successTest() {
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
}
