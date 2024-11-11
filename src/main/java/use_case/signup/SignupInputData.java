package use_case.signup;

/**
 * The Input Data for the Signup Use Case.
 */
public class SignupInputData {

    private final String username;
    private final String email;
    private final String password;
    private final String repeatPassword;
    private final boolean isClub;

    public SignupInputData(String username, String email, String password, String repeatPassword, boolean isClub) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.isClub = isClub;
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

    public boolean getIsClub() {
        return isClub;
    }
}
