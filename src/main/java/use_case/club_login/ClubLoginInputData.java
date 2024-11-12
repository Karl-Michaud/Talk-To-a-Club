package use_case.club_login;

/**
 * The input data for the login use case for clubs.
 */
public class ClubLoginInputData {
    private final String email;
    private final String password;

    public ClubLoginInputData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
