package use_case.signup.club_signup;

/**
 * The Input Data for the Club Signup Use Case.
 */
public class ClubSignupInputData {

    private final String username;
    private final String email;
    private final String password;
    private final String repeatPassword;

    public ClubSignupInputData(String username, String email, String password, String repeatPassword) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getUsername() {
        return username;
    }

    String getEmail() {
        return email;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
