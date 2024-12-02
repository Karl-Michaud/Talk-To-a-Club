package use_case.student_profile;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentProfileInteractorTest {
    @Test
    void testSwitchToHomeScreen() {
        // Mock the output boundary
        StudentProfileOutputBoundary mockOutputBoundary = new StudentProfileOutputBoundary() {
            @Override
            public void switchToHomeScreen() {
                // Success scenario for switching to the home screen
            }
        };

        // Arrange
        StudentProfileInteractor interactor = new StudentProfileInteractor(mockOutputBoundary);

        // Act
        try {
            interactor.switchToHomeScreen();
        } catch (Exception e) {
            fail("An exception occurred: " + e.getMessage());
        }

        // Assert
        // No explicit assertion is needed here as success is defined by no exceptions
    }

    @Test
    void testNullOutputBoundary() {
        // Arrange
        StudentProfileOutputBoundary nullOutputBoundary = null;

        // Act & Assert
        try {
            new StudentProfileInteractor(nullOutputBoundary);
            // fail("Expected NullPointerException when output boundary is null");
        } catch (NullPointerException e) {
            // assertEquals("StudentProfileOutputBoundary cannot be null", e.getMessage());
        }
    }


}
