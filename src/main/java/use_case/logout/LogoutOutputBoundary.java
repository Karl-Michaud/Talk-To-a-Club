package use_case.logout;

/**
 * The output boundary for the Student Signup Use Case.
 */
public interface LogoutOutputBoundary {

    /**
     * Switches to the Login View with an empty login state.
     */
    void switchToLoginView();
}
