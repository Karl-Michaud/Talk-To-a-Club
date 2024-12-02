package use_case.student_homepage;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class StudentHomeInteractorTest {

    @Test
    void successTest() {
        // This creates a successPresenter that tests whether the test case is as we expect.
        // This use case only handles switching to the profile view, so it's a bit empty.
        StudentHomeOutputBoundary successPresenter = new StudentHomeOutputBoundary() {

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected");
            }

            @Override
            public void switchToProfileView() {
                // expected to succeed
            }
        };
        final StudentHomeInputBoundary interactor = new StudentHomeInteractor (successPresenter);
        interactor.switchToProfileView();
    }

    @Test
    void testStudentHomeInputData() {
        // Arrange
        String testEmail = "student@university.com";
        StudentHomeInputData inputData = new StudentHomeInputData(testEmail);

        // Act
        String resultEmail = inputData.getEmail();

        // Assert
        assertEquals(testEmail, resultEmail);
    }
}
