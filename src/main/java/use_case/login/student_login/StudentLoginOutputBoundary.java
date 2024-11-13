package use_case.login.student_login;

/**
 * The output boundary for the student Login Use Case.
 */
public interface StudentLoginOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case for students.
     * @param studentLoginOutputData the output data
     */
    void prepareSuccessView(StudentLoginOutputData studentLoginOutputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Student Home View.
     */
    void switchToStudentHomeView();
}
