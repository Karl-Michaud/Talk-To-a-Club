package interface_adapter.login.club_login;

/**
 * The state for the Club Login View Model.
 */
public class ClubLoginState {
    private String email = "";
    private String password = "";
    private String loginError;

    public String getEmail() {
        return email;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClubLoginState{"
                + "email='" + email + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
