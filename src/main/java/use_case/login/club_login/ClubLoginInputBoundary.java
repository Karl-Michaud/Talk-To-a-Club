package use_case.login.club_login;

/**
 * Login input boundary for the login use case for clubs.
 */
public interface ClubLoginInputBoundary {
    /**
     * Executes the login use case for clubs.
     * @param clubLoginInputData the input data
     */
    void execute(ClubLoginInputData clubLoginInputData);

    /**
     * Switches to the Club Home View.
     */
    void switchToClubHomeView();

    /**
     * Switches to the Club Signup View.
     */
    void switchToClubSignupView();
}
