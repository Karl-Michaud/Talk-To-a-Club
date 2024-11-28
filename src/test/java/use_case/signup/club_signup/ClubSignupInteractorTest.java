package use_case.signup.club_signup;

import data_access.InMemoryUserDataAccessObject;
import entity.user.Club;
import entity.user.ClubUserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClubSignupInteractorTest {

    @Test
    void successTest() {
        StudentSignupInputData inputData = new StudentSignupInputData("test club", "roy@gmail.com",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

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
        StudentSignupInputData inputData = new StudentSignupInputData("test club", "roy@gmail.com",
                "password", "password");

        // Uses an in memory database to test the use case and stores a Club with the same name
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.saveClub(new Club("test club", "ok@k.com", "pass", null, null));


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
        StudentSignupInputData inputData = new StudentSignupInputData("test club", "ok@k.com",
                "password", "password");

        // Uses an in memory database to test the use case and stores a Club with the same email
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        userRepository.saveClub(new Club("test club", "ok@k.com", "pass", null, null));


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
        StudentSignupInputData inputData = new StudentSignupInputData("test club", "ok@k.com",
                "password1", "password2");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

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
        StudentSignupInputData inputData = new StudentSignupInputData("", "ok@k.com",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

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
        StudentSignupInputData inputData = new StudentSignupInputData(username, "ok@k.com",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

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
        StudentSignupInputData inputData = new StudentSignupInputData("ok", "ok@k.com",
                "passwo", "passwo");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

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
        StudentSignupInputData inputData = new StudentSignupInputData("ok", "ok@k.com",
                password, password);

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

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
        StudentSignupInputData inputData = new StudentSignupInputData("ok", "ok@kcom",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

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
        StudentSignupInputData inputData = new StudentSignupInputData("ok", "okk.com",
                "password", "password");

        // Uses an in memory database to test the use case
        ClubSignupUserDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

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
}
