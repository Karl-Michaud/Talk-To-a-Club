package use_case.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {
    private final String username;
    private final String email;
    private final boolean useCaseFailed;

    public LoginOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.email = null;
        this.useCaseFailed = useCaseFailed;
    }

    public LoginOutputData(String username, String email, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
}
