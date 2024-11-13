package interface_adapter.login;

/**
 * The state for the Login View Model.
 */
public class LoginState {
    private String identifier = "";
    private String password = "";
    private String loginError;

    public String getIdentifier() {
        return identifier;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
                + "identifier='" + identifier + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
