package interface_adapter.login.club_login;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Club Signup View.
 */
public class ClubLoginViewModel extends ViewModel<ClubLoginState> {

    public ClubLoginViewModel() {
        super("club login");
        setState(new ClubLoginState());
    }
}