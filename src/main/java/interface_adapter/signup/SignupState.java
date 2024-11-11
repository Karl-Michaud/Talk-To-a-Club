package interface_adapter.signup;

/**
 * The state for the Signup View Model.
 */
public class SignupState {
    private String username = "";
    private String email = "";
    private String password = "";
    private String repeatPassword = "";
    private String signupError;
    private boolean signupClub = false;

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getSignupError() {
        return this.signupError;
    }

    public boolean getSignupClub() {
        return this.signupClub;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setSignupError(String error) {
        this.signupError = error;
    }

    public void setSignupClub(boolean signupClub) {
        this.signupClub = signupClub;
    }

    @Override
    public String toString() {
        return "SignupState{"
                + "username='" + username + '\''
                + "email='" + email + '\''
                + ", password='" + password + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + '}';
    }
}
