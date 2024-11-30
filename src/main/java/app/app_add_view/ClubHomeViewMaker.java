package app.app_add_view;

import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import view.ClubLoggedInView;

/**
 * Class creates the Club Home view (ClubLoggedInView).
 */
public class ClubHomeViewMaker {
    private final ClubLoggedInView clubLoggedInView;
    private final ClubLoggedInViewModel clubLoggedInViewModel;

    public ClubHomeViewMaker() {
        clubLoggedInViewModel = new ClubLoggedInViewModel();
        clubLoggedInView = new ClubLoggedInView(clubLoggedInViewModel);
    }

    public ClubLoggedInView getClubLoggedInView() {
        return clubLoggedInView;
    }

    public ClubLoggedInViewModel getClubLoggedInViewModel() {
        return clubLoggedInViewModel;
    }
}
