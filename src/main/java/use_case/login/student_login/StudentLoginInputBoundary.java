package use_case.login.student_login;

/**
 * Input boundary for the login use case for student users.
 */
public interface StudentLoginInputBoundary {
    /**
     * Executes the login use case for the student user.
     * @param studentLoginInputData the input data
     */
    void execute(StudentLoginInputData studentLoginInputData);

    /**
     * Switches to the Student Home View.
     */
    void switchToStudentHomeView();
}
