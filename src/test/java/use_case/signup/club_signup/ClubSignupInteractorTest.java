package use_case.signup.club_signup;

import data_access.InMemoryUserDataStudentAccessObject;
import entity.user.Club;
import entity.user.ClubFactory;
import entity.user.ClubUserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClubSignupInteractorTest {

    @Test
    void successTest() {
        ClubSignupInputData inputData = new ClubSignupInputData("test club", "roy@gmail.com",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                // Checks that the output data is correct and that the name and email exists in the database
                assertEquals("roy@gmail.com", user.getEmail());
                assertTrue(userRepository.existsByEmailClub("roy@gmail.com"));
                assertTrue(userRepository.existsByNameClub("test club"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };

        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void usernameAlreadyExistsTest() {
        ClubSignupInputData inputData = new ClubSignupInputData("test club", "roy@gmail.com",
                "password", "password");

        // Uses an in memory database to test the use case and stores a Club with the same name
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();
        ClubFactory clubFactory = new ClubUserFactory();
        userRepository.saveClub(clubFactory.create("test club", "ok@k.com", "pass"));


        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void emailAlreadyExistsTest() {
        ClubSignupInputData inputData = new ClubSignupInputData("test", "ok@k.com",
                "password", "password");

        // Uses an in memory database to test the use case and stores a Club with the same email
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        ClubFactory clubFactory = new ClubUserFactory();
        userRepository.saveClub(clubFactory.create("test club", "ok@k.com", "pass"));


        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Email address already exists.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void passwordsNotMatchTest() {
        ClubSignupInputData inputData = new ClubSignupInputData("test club", "ok@k.com",
                "password1", "password2");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Passwords don't match.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void usernameShortTest() {
        ClubSignupInputData inputData = new ClubSignupInputData("", "ok@k.com",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username must be at least 1 character(s).", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void usernameLongTest() {
        String username = "1".repeat(65);
        ClubSignupInputData inputData = new ClubSignupInputData(username, "ok@k.com",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Username must be at most 64 character(s).", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void shortPasswordTest() {
        ClubSignupInputData inputData = new ClubSignupInputData("ok", "ok@k.com",
                "passwo", "passwo");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password must be at least 8 character(s).", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void longPasswordTest() {
        String password = "1".repeat(65);
        ClubSignupInputData inputData = new ClubSignupInputData("ok", "ok@k.com",
                password, password);

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Password must be at most 64 character(s).", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void emailNoDotTest() {
        ClubSignupInputData inputData = new ClubSignupInputData("ok", "ok@kcom",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid email address.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void emailNoAtTest() {
        ClubSignupInputData inputData = new ClubSignupInputData("ok", "okk.com",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Invalid email address.", error);
            }

            @Override
            public void switchToLoginView() {
                fail("Use case switch to login is unexpected.");
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void switchToLoginTest() {
        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataStudentAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        ClubSignupOutputBoundary successPresenter = new ClubSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubSignupOutputData user) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case fail is unexpected.");
            }

            @Override
            public void switchToLoginView() {
                // This is expected to pass (everything is done by the presenter implementation)
            }
        };
        ClubSignupInputBoundary interactor = new ClubSignupInteractor(userRepository, successPresenter, new ClubUserFactory());
        interactor.switchToLoginView();
    }
}
