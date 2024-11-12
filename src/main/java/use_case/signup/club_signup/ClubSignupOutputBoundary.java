package use_case.signup.club_signup;

/**
 * The output boundary for the Club Signup Use Case.
 */
public interface ClubSignupOutputBoundary {

    /**
     * Prepares the success view for the Club Signup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ClubSignupOutputData outputData);

    /**
     * Prepares the failure view for the Student Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();
}
