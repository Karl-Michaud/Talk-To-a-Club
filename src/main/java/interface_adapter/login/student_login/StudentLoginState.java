package interface_adapter.login.student_login;

/**
 * The state for the Student Login View Model.
 */
public class StudentLoginState {
    private String username = "";
    private String password = "";
    private String loginError;

    public String getUsername() {
        return username;
    }

    public String getLoginError() {
        return loginError;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLoginError(String usernameError) {
        this.loginError = usernameError;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "StudentLoginState{"
                + "username='" + username + '\''
                + ", password='" + password + '\''
                + '}';
    }
}
