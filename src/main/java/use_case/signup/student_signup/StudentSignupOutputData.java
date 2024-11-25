package use_case.signup.student_signup;

/**
 * Output Data for the Student Signup Use Case.
 */
public class StudentSignupOutputData {

    private final String email;

    private final boolean useCaseFailed;

    public StudentSignupOutputData(String email, boolean useCaseFailed) {
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
