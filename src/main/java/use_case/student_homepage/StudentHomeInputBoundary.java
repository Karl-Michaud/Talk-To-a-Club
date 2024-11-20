package use_case.student_homepage;

/**
 * InputBoundary for the StudentHome usecase.
 */
public interface StudentHomeInputBoundary {
    /**
     * Executes the StudentHome InputData.
     * @param student the current student using the application.
     */
    void execute(StudentHomeInputData student);

    /**
     * Switches to LoginView.
     */
    void switchToLoginView();

    /**
     * Switches to ProfileView.
     */
    void switchToProfileView();
}
