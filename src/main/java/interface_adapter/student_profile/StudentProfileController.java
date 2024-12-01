package interface_adapter.student_profile;

import use_case.student_profile.StudentProfileInputBoundary;

/**
 * The controller for the student profile page.
 */
public class StudentProfileController {
    private final StudentProfileInputBoundary studentProfileUseCaseInteractor;

    public StudentProfileController(StudentProfileInputBoundary studentProfileUseCaseInteractor) {
        this.studentProfileUseCaseInteractor = studentProfileUseCaseInteractor;
    }

    /**
     * Switches from the profile page back to the student home screen.
     */
    public void switchToHomeScreen() {
        studentProfileUseCaseInteractor.switchToHomeScreen();
    }
}
