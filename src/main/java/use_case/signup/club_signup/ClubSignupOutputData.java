package use_case.signup.club_signup;

/**
 * Output Data for the Club Signup Use Case.
 * Note that this is currently very empty, but is here for potential future expansions.
 */
public class ClubSignupOutputData {

    private final String email;

    public ClubSignupOutputData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
