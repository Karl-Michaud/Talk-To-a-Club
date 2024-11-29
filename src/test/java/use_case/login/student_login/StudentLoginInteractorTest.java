package use_case.login.student_login;

import data_access.InMemoryUserDataAccessObject;
import entity.user.Student;
import entity.user.StudentFactory;
import entity.user.StudentUserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Tests for the student login use case. Impossible to get 100% coverage due to switch method.
 */
public class StudentLoginInteractorTest {
    static String studentUsername = "student";
    static String studentEmail = "student@email.com";
    static String studentPassword = "student12345";

    @Test
    public void successTest() {
        // Initialise Student Factory
        StudentFactory studentFactory = new StudentUserFactory();

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create the student (register)
        Student testStudent = studentFactory.create(studentUsername, studentEmail, studentPassword);

        // Save student to DB (register use case execution)
        userRepository.saveStudent(testStudent);

        // Create the input data for student login use case
        StudentLoginInputData inputData = new StudentLoginInputData(testStudent.getEmail(), testStudent.getPassword());

        // Create the successPresenter that tests whether the test case is as we expect.
        StudentLoginOutputBoundary successPresenter = new StudentLoginOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentLoginOutputData studentLoginOutputData) {
                assertEquals(false, studentLoginOutputData.useCaseFailed());

                // Verify that the test student has same values
                assertEquals(testStudent.getEmail(), studentLoginOutputData.getEmail());
                assertEquals(testStudent.getUsername(), studentLoginOutputData.getUsername());

                // Verify that the database has the same info
                Student dbStudent = userRepository.getStudent(studentLoginOutputData.getEmail());
                assertEquals(dbStudent.getEmail(), studentLoginOutputData.getEmail());
                assertEquals(dbStudent.getUsername(), studentLoginOutputData.getUsername());

                // Check that the number of clubs joined is 0 since it is a new student
                int sizeJoinedClubs = dbStudent.getJoinedClubs().size();
                assertEquals(0, sizeJoinedClubs);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToStudentSignupView() {
                // ignore since this will not be called during tests
            }
        };

        // Execute the use case that need to be tested.
        StudentLoginInputBoundary interactor = new StudentLoginInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureTestEmptyField() {
        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create the input data
        StudentLoginInputData inputDataNoEmail = new StudentLoginInputData("", studentPassword);
        StudentLoginInputData inputDataNoPassword = new StudentLoginInputData(studentEmail, "");
        StudentLoginInputData inputDataNoting = new StudentLoginInputData("", "");

        // Create the failurePresenter that tests whether the test case is as we expect.
        StudentLoginOutputBoundary failurePresenter = new StudentLoginOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentLoginOutputData studentLoginOutputData) {
               fail("Error not caught: text field(s) empty.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Empty text field(s).", errorMessage);
            }

            @Override
            public void switchToStudentSignupView() {
                // ignore since this will not be called during tests
            }
        };

        // Execute the use case that need to be tested.
        StudentLoginInputBoundary interactor = new StudentLoginInteractor(userRepository,failurePresenter);
        interactor.execute(inputDataNoEmail);
        interactor.execute(inputDataNoPassword);
        interactor.execute(inputDataNoting);
    }

    @Test
    public void failureTestInvalidEmail() {
        // Initialise Student Factory
        StudentFactory studentFactory = new StudentUserFactory();

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create the student (register)
        Student testStudent = studentFactory.create(studentUsername, studentEmail, studentPassword);

        // Save student to DB (register use case execution)
        userRepository.saveStudent(testStudent);

        // Create the input data for student login use case
        String wrongEmail = "wrong@email.com";
        StudentLoginInputData inputData = new StudentLoginInputData(wrongEmail, testStudent.getPassword());

        // Create the failurePresenter that tests whether the test case is as we expect.
        StudentLoginOutputBoundary failurePresenter = new StudentLoginOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentLoginOutputData studentLoginOutputData) {
                fail("Error npt caught: invalid email.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(wrongEmail + ": Account does not exist.", errorMessage);
            }

            @Override
            public void switchToStudentSignupView() {
                // ignore since this will not be called during tests
            }
        };

        // Execute the use case that need to be tested.
        StudentLoginInputBoundary interactor = new StudentLoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureTestInvalidPassword() {
        // Initialise Student Factory
        StudentFactory studentFactory = new StudentUserFactory();

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create the student (register)
        Student testStudent = studentFactory.create(studentUsername, studentEmail, studentPassword);

        // Save student to DB (register use case execution)
        userRepository.saveStudent(testStudent);

        // Create the input data for student login use case
        String wrongPassword = "wrong";
        StudentLoginInputData inputData = new StudentLoginInputData(testStudent.getEmail(), wrongPassword);

        // Create the failurePresenter that tests whether the test case is as we expect.
        StudentLoginOutputBoundary failurePresenter = new StudentLoginOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentLoginOutputData studentLoginOutputData) {
                fail("Error not caught: invalid password");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Incorrect password for \"" + testStudent.getEmail() + "\".", errorMessage);
            }

            @Override
            public void switchToStudentSignupView() {
                // ignore since this will not be called during tests
            }
        };

        // Execute the use case that need to be tested.
        StudentLoginInputBoundary interactor = new StudentLoginInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }
}
