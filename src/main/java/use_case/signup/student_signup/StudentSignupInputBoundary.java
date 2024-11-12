package use_case.signup.student_signup;

/**
 * Input Boundary for actions which are related to signing up a student.
 */
public interface StudentSignupInputBoundary {

    /**
     * Executes the student signup use case.
     * @param studentSignupInputData the input data
     */
    void execute(StudentSignupInputData studentSignupInputData);

    /**
     * Executes the switch to login view use case.
     */
    void switchToLoginView();
}
