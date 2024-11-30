package app.app_add_view;

import interface_adapter.signup.club_signup.ClubSignupViewModel;
import view.ClubSignupView;

/**
 * Class creates the Club Sign up View.
 */
public class ClubSignupViewMaker {
    private final ClubSignupViewModel clubSignupViewModel;
    private final ClubSignupView clubSignupView;

    public ClubSignupViewMaker() {
        clubSignupViewModel = new ClubSignupViewModel();
        clubSignupView = new ClubSignupView(clubSignupViewModel);
    }

    public ClubSignupViewModel getClubSignupViewModel() {
        return clubSignupViewModel;
    }

    public ClubSignupView getClubSignupView() {
        return clubSignupView;
    }
}
