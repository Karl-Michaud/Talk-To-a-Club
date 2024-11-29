package use_case.signup.student_signup;

/**
 * Output Data for the Student Signup Use Case.
 * Note that this is currently very empty, but is here for potential future expansions.
 */
public class StudentSignupOutputData {

    private final String email;

    public StudentSignupOutputData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
