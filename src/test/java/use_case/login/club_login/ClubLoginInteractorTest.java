package use_case.login.club_login;

import data_access.InMemoryUserDataAccessObject;
import entity.user.Club;
import entity.user.ClubFactory;
import entity.user.ClubUserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Test for the club login use case. Impossible to get 100% coverage due to switch method
 */
public class ClubLoginInteractorTest {
    static String clubName = "Test Club Name";
    static String clubEmail = "club@email.com";
    static String clubPassword = "TestClubPassword123";

    @Test
    public void successTest() {
        // Initialise the club factory
        ClubFactory clubFactory = new ClubUserFactory();

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a club (sign up)
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Save Club to the DB (execute sign up use case)
        userRepository.saveClub(testClub);

        // Create the input data for create post use case.
        ClubLoginInputData inputData = new ClubLoginInputData(testClub.getEmail(), testClub.getPassword());


        // Create the successPresenter that tests whether the test case is as we expect.
        ClubLoginOutputBoundary successPresenter = new ClubLoginOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubLoginOutputData clubLoginOutputData) {
                assertEquals(false, clubLoginOutputData.useCaseFailed());
                // Verify that the test club has same values
                assertEquals(testClub.getEmail(), clubLoginOutputData.getEmail());
                assertEquals(testClub.getUsername(), clubLoginOutputData.getUsername());

                // Verify that the database has same info
                Club dbClub = userRepository.getClub(testClub.getEmail());
                assertEquals(dbClub.getEmail(), clubLoginOutputData.getEmail());
                assertEquals(dbClub.getUsername(), clubLoginOutputData.getUsername());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToClubSignupView() {
                // Normal to be empty since there is no way to test switch
            }
        };
        // Execute the use case that need to be tested.
        ClubLoginInputBoundary interactor = new ClubLoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureTestAccountDNE() {
        // Initialise the club factory
        ClubFactory clubFactory = new ClubUserFactory();

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a club (sign up)
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Save Club to the DB (execute sign up use case)
        userRepository.saveClub(testClub);

        // Create the input data for create post use case.
        String wrongEmail = "wrong@email.com";
        ClubLoginInputData inputData = new ClubLoginInputData(wrongEmail, testClub.getPassword());

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubLoginOutputBoundary failurePresenter = new ClubLoginOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubLoginOutputData clubLoginOutputData) {
                fail("Error not caught: user does not exist.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(wrongEmail + ": Account does not exist.", errorMessage);
            }

            @Override
            public void switchToClubSignupView() {
                // Normal to be empty since there is no way to test switch
            }
        };
        // Execute the use case that need to be tested.
        ClubLoginInputBoundary interactor = new ClubLoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureTestWrongPassword() {
        // Initialise the club factory
        ClubFactory clubFactory = new ClubUserFactory();

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a club (sign up)
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Save Club to the DB (execute sign up use case)
        userRepository.saveClub(testClub);

        // Create the input data for create post use case.
        String wrongPassword = "wrong";
        ClubLoginInputData inputData = new ClubLoginInputData(testClub.getEmail(), wrongPassword);

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubLoginOutputBoundary failurePresenter = new ClubLoginOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubLoginOutputData clubLoginOutputData) {
                fail("Error not caught: wrong password.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Incorrect password for \"" + testClub.getEmail() + "\".", errorMessage);
            }

            @Override
            public void switchToClubSignupView() {
                // Normal to be empty since there is no way to test switch
            }
        };
        // Execute the use case that need to be tested.
        ClubLoginInputBoundary interactor = new ClubLoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureTestEmptyField() {
        // Initialise the club factory
        ClubFactory clubFactory = new ClubUserFactory();

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a club (sign up)
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Save Club to the DB (execute sign up use case)
        userRepository.saveClub(testClub);

        // Create the input data for create post use case.
        ClubLoginInputData inputDataNoPassword = new ClubLoginInputData(testClub.getEmail(), "");
        ClubLoginInputData inputDataNoEmail = new ClubLoginInputData("", testClub.getPassword());
        ClubLoginInputData inputDataNothing = new ClubLoginInputData("", "");

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubLoginOutputBoundary failurePresenter = new ClubLoginOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubLoginOutputData clubLoginOutputData) {
                fail("Error not caught: empty field(s)");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Empty text field(s).", errorMessage);
            }

            @Override
            public void switchToClubSignupView() {
                // Normal to be empty since there is no way to test switch
            }
        };
        // Execute the use case that need to be tested.
        ClubLoginInputBoundary interactor = new ClubLoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputDataNoEmail);
        interactor.execute(inputDataNoPassword);
        interactor.execute(inputDataNothing);
    }
}
