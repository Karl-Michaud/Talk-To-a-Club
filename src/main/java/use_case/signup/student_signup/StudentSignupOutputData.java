package use_case.signup.student_signup;

/**
 * Output Data for the Student Signup Use Case.
 */
public class StudentSignupOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public StudentSignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
