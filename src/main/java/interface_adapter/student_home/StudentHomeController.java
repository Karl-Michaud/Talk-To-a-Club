package interface_adapter.student_home;

import use_case.student_homepage.StudentHomeInputBoundary;
import use_case.student_homepage.StudentHomeInputData;

/**
 * Controller for the StudentHome view.
 */
public class StudentHomeController {

    private final StudentHomeInputBoundary studentHomeUseCaseInteractor;

    public StudentHomeController(StudentHomeInputBoundary studentHomeUseCaseInteractor) {
        this.studentHomeUseCaseInteractor = studentHomeUseCaseInteractor;
    }

    /**
     * Executes the Controller.
     * @param query the user input for the search field.
     * @param email the current users email.
     */
    public void execute(String query, String email) {

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
