package interface_adapter.signup.student_signup;

import use_case.signup.student_signup.StudentSignupInputBoundary;
import use_case.signup.student_signup.StudentSignupInputData;

/**
 * Controller for the Club Signup Use Case.
 */
public class StudentSignupController {

    private final StudentSignupInputBoundary userSignupUseCaseInteractor;

    public StudentSignupController(StudentSignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Student Signup Use Case.
     * @param username the username to sign up
     * @param email the email to sign up
     * @param password1 the password
     * @param password2 the password repeated
     */
    public void execute(String username, String email, String password1, String password2) {
        final StudentSignupInputData signupInputData = new StudentSignupInputData(
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
