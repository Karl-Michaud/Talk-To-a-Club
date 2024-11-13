package interface_adapter.login.student_login;

import use_case.login.LoginInputBoundary;

/**
 * The Controller for the Student Login Use Case
 */
public class StudentLoginController {
    /**
     *
     */
    private final LoginInputBoundary loginUseCaseInteractor;

    /**
     * The Constructor for the Student Login controller
     */
    public StudentLoginController(LoginInputBoundary loginUseCaseInteractor){
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Student Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password){
        final ClubLoginInputData loginInputData = new ClubLoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

    public void switchToClubSignupView() {
        loginUseCaseInteractor.switchToClubSignupView();
    }

    public void switchToStudentSignupView() {
        loginUseCaseInteractor.switchToStudentSignupView();
    }
}
