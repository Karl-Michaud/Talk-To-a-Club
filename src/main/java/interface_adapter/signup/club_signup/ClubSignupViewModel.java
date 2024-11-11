package interface_adapter.signup.club_signup;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Club Signup View.
 */
public class ClubSignupViewModel extends ViewModel<ClubSignupState> {

    public ClubSignupViewModel() {
        super("club sign up");
        setState(new ClubSignupState());
    }

}
