package interface_adapter.login.club_login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

/**
 * The Controller for the Club Login Use Case
 */
public class ClubLoginController {
    /**
     *
     */
    private final LoginInputBoundary loginUseCaseInteractor;

    /**
     * The Constructor for the Club Login controller
     */
    public ClubLoginController(LoginInputBoundary loginUseCaseInteractor){
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Club Login Use Case.
     * @param email the email of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String email, String password){
        final ClubLoginInputData loginInputData = new ClubLoginInputData(email, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

    public void switchToClubSignupView() {
        loginUseCaseInteractor.switchToClubSignupView();
    }

    public void switchToStudentSignupView() {
        loginUseCaseInteractor.switchToStudentSignupView();
    }
}
