package interface_adapter.signup.club_signup;

/**
 * The state for the Club Signup View Model.
 */
public class ClubSignupState {
    private String username = "";
    private String email = "";
    private String password = "";
    private String repeatPassword = "";
    private String signupError;

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

    @Override
    public String toString() {
        return "ClubSignupState{"
                + "username='" + username + '\''
                + "email='" + email + '\''
                + ", password='" + password + '\''
                + ", repeatPassword='" + repeatPassword + '\''
                + '}';
    }
}
