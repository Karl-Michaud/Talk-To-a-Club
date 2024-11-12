package use_case.signup.club_signup;

/**
 * Input Boundary for actions which are related to signing up a club.
 */
public interface ClubSignupInputBoundary {

    /**
     * Executes the club signup use case.
     * @param clubSignupInputData the input data
     */
    void execute(ClubSignupInputData clubSignupInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}
