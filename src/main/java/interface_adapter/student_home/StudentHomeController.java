package interface_adapter.student_home;

import use_case.student_homepage.StudentHomeInputBoundary;

/**
 * Controller for the StudentHome view.
 */
public class StudentHomeController {

    private final StudentHomeInputBoundary studentHomeUseCaseInteractor;

    public StudentHomeController(StudentHomeInputBoundary studentHomeUseCaseInteractor) {
        this.studentHomeUseCaseInteractor = studentHomeUseCaseInteractor;
    }

    /**
     * Switches from HomeView to LoginView.
     */
    public void switchToLoginView() {
        studentHomeUseCaseInteractor.switchToLoginView();
    }

    /**
     * Switches from HomeView to ProfileView.
     */
    public void switchToProfileView() {
        studentHomeUseCaseInteractor.switchToProfileView();
    }
}
