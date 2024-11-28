package interface_adapter.student_profile;

import use_case.student_profile.StudentProfileInputBoundary;

public class StudentProfileController {
    private final StudentProfileInputBoundary studentProfileUseCaseInteractor;

    public StudentProfileController(StudentProfileInputBoundary studentProfileUseCaseInteractor) {
        this.studentProfileUseCaseInteractor = studentProfileUseCaseInteractor;
    }

    public void switchToHomeScreen() {
        studentProfileUseCaseInteractor.switchToHomeScreen();
    }
}
