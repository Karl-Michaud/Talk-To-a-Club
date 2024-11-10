package interface_adapter.signup.club_signup;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
public class ClubSignupViewModel extends ViewModel<ClubSignupState> {

    public static final String TITLE_LABEL = "Club Sign Up View";
    public static final String CLUB_USERNAME_LABEL = "Choose club username";
    public static final String EMAIL_LABEL = "Choose email";
    public static final String PASSWORD_LABEL = "Choose password";
    public static final String REPEAT_PASSWORD_LABEL = "Enter password again";

    public static final String SIGNUP_BUTTON_LABEL = "Sign up";
    public static final String CANCEL_BUTTON_LABEL = "Cancel";

    public ClubSignupViewModel() {
        super("club sign up");
        setState(new ClubSignupState());
    }

}
