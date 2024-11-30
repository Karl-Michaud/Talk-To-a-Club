package use_case.signup.student_signup;

import data_access.InMemoryUserDataAccessObject;
import entity.user.StudentFactory;
import entity.user.StudentUserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

public class StudentSignupInteractorTest {

    @Test
    void successTest() {
        StudentSignupInputData inputData = new StudentSignupInputData("Roy", "roy@gmail.com",
                "password", "password");

        // Uses an in memory database to test the use case
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
                // Checks that the output data is correct and that the name and email exists in the database
                assertEquals("roy@gmail.com", user.getEmail());
                assertTrue(userRepository.existsByEmailStudent("roy@gmail.com"));
                assertTrue(userRepository.existsByNameStudent("Roy"));
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

        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void usernameAlreadyExistsTest() {
        StudentSignupInputData inputData = new StudentSignupInputData("test club", "roy@gmail.com",
                "password", "password");

        // Uses an in memory database to test the use case and stores a student with the same name
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        StudentFactory studentFactory = new StudentUserFactory();
        userRepository.saveStudent(studentFactory.create("test club", "ok@k.com", "pass"));

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void emailAlreadyExistsTest() {
        StudentSignupInputData inputData = new StudentSignupInputData("test", "ok@k.com",
                "password", "password");

        // Uses an in memory database to test the use case and stores a student with the same email
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();
        StudentFactory studentFactory = new StudentUserFactory();
        userRepository.saveStudent(studentFactory.create("test club", "ok@k.com", "pass"));

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void passwordsNotMatchTest() {
        StudentSignupInputData inputData = new StudentSignupInputData("test club", "ok@k.com",
                "password1", "password2");

        // Uses an in memory database to test the use case
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void usernameShortTest() {
        StudentSignupInputData inputData = new StudentSignupInputData("", "ok@k.com",
                "password", "password");

        // Uses an in memory database to test the use case
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void usernameLongTest() {
        String username = "1".repeat(65);
        StudentSignupInputData inputData = new StudentSignupInputData(username, "ok@k.com",
                "password", "password");

        // Uses an in memory database to test the use case
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void shortPasswordTest() {
        StudentSignupInputData inputData = new StudentSignupInputData("ok", "ok@k.com",
                "passwo", "passwo");

        // Uses an in memory database to test the use case
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void longPasswordTest() {
        String password = "1".repeat(65);
        StudentSignupInputData inputData = new StudentSignupInputData("ok", "ok@k.com",
                password, password);

        // Uses an in memory database to test the use case
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void emailNoDotTest() {
        StudentSignupInputData inputData = new StudentSignupInputData("ok", "ok@kcom",
                "password", "password");

        // Uses an in memory database to test the use case
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void emailNoAtTest() {
        StudentSignupInputData inputData = new StudentSignupInputData("ok", "okk.com",
                "password", "password");

        // Uses an in memory database to test the use case
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.execute(inputData);
    }

    @Test
    void switchToLoginTest() {
        // Uses an in memory database to test the use case
        StudentSignupDataAccessInterface userRepository = new InMemoryUserDataAccessObject();

        // This creates a successPresenter that tests whether the test case is as we expect.
        StudentSignupOutputBoundary successPresenter = new StudentSignupOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentSignupOutputData user) {
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
        StudentSignupInputBoundary interactor = new StudentSignupInteractor(userRepository, successPresenter, new StudentUserFactory());
        interactor.switchToLoginView();
    }
}
