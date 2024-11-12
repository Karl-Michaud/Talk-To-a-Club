package use_case.signup.club_signup;

/**
 * Output Data for the Club Signup Use Case.
 */
public class ClubSignupOutputData {

    private final String email;

    private final boolean useCaseFailed;

    public ClubSignupOutputData(String email, boolean useCaseFailed) {
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
