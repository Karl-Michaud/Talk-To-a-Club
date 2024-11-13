package interface_adapter.login.student_login;

import use_case.login.student_login.StudentLoginInputBoundary;
import use_case.login.student_login.StudentLoginInputData;

/**
 * The Controller for the Student Login Use Case
 */
public class StudentLoginController {
    /**
     *
     */
    private final StudentLoginInputBoundary loginUseCaseInteractor;

    /**
     * The Constructor for the Student Login controller
     */
    public StudentLoginController(StudentLoginInputBoundary loginUseCaseInteractor){
        this.loginUseCaseInteractor = loginUseCaseInteractor;
    }

    /**
     * Executes the Student Login Use Case.
     * @param username the username of the user logging in
     * @param password the password of the user logging in
     */
    public void execute(String username, String password){
        final StudentLoginInputData loginInputData = new StudentLoginInputData(username, password);

        loginUseCaseInteractor.execute(loginInputData);
    }

    /**
     * Executes the "switch to StudentHomeView" Use Case.
     */
    public void switchToLoginView() {
        loginUseCaseInteractor.switchToStudentHomeView();
    }

    /**
     * Executes the "switch to StudentSignupView" Use Case.
     */
    public void switchToStudentSignupView() {
        loginUseCaseInteractor.switchToStudentSignupView();
        System.out.print('B');
    }
}
