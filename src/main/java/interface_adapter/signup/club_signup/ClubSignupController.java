package interface_adapter.signup.club_signup;

import use_case.signup.club_signup.ClubSignupInputBoundary;
import use_case.signup.club_signup.ClubSignupInputData;

/**
 * Controller for the Club Signup Use Case.
 */
public class ClubSignupController {

    private final ClubSignupInputBoundary userSignupUseCaseInteractor;

    public ClubSignupController(ClubSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param username the username to sign up
     * @param email the email to sign up
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(String username, String email, String password1, String password2) {
        final ClubSignupInputData signupInputData = new ClubSignupInputData(
                username, email, password1, password2);

        userSignupUseCaseInteractor.execute(signupInputData);
    }

    /**
     * Executes the "switch to LoginView" Use Case.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }
}
