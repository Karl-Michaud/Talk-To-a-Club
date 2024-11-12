package use_case.signup.student_signup;

/**
 * The output boundary for the Student Signup Use Case.
 */
public interface StudentSignupOutputBoundary {

    /**
     * Prepares the success view for the Student Signup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(StudentSignupOutputData outputData);

    /**
     * Prepares the failure view for the Student Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();
}
