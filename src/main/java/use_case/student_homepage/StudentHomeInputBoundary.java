package use_case.student_homepage;

/**
 * InputBoundary for the StudentHome usecase.
 */
public interface StudentHomeInputBoundary {
    /**
     * Switches to LoginView.
     */
    void switchToLoginView();

    /**
     * Switches to ProfileView.
     */
    void switchToProfileView();
}
