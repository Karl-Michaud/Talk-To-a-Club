package interface_adapter.club_home_refresh;

import use_case.signup.club_signup.ClubSignupInputBoundary;
import use_case.signup.club_signup.ClubSignupInputData;

/**
 * Controller for the Club Home Refresh Use Case.
 */
public class ClubHomeRefreshController {

    private final ClubHomeRefreshInputBoundary clubHomeRefreshInteractor;

    public ClubHomeRefreshController(ClubHomeRefreshInputBoundary clubHomeRefreshInteractor) {
        this.clubHomeRefreshInteractor = clubHomeRefreshInteractor;
    }

    /**
     * Executes the Club Home Refresh Use Case.
     * @param email the email of the logged in club
     */
    public void execute(String email) {
        final ClubHomeRefreshInputData clubRefreshData = new ClubHomeRefreshInputData(email);

        clubHomeRefreshInteractor.execute(clubRefreshData);
    }
}
