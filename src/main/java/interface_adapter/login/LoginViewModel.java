package interface_adapter.login;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Signup View.
 */
public class LoginViewModel extends ViewModel<LoginState> {

    public static final String TITLE_LABEL = "Login View";
    public static final String EMAIL_LABEL = "Enter email";
    public static final String PASSWORD_LABEL = "Enter password";


    public static final String LOGIN_BUTTON_LABEL = "Login";
    public static final String CLUB_SIGNUP_LABEL = "Club Signup";
    public static final String STUDENT_SIGNUP_LABEL = "Student Signup";

    public LoginViewModel() {
        super("club sign up");
        setState(new ClubSignupState());
    }



}