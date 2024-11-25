package interface_adapter.club_logged_in;

import entity.data_structure.DataStore;

/**
 * Club Logged In state.
 */
public class ClubLoggedInState {
    private String clubName = "";

    private String email = "";
    private String emailError;

    private String password = "";
    private String passwordError;

    public ClubLoggedInState(ClubLoggedInState copy) {
        this.clubName = copy.clubName;

        this.email = copy.email;
        this.emailError = copy.emailError;

        this.password = copy.password;
        this.passwordError = copy.passwordError;
    }

    public ClubLoggedInState() {
        // empty body since we have a constructor already and so no default constructor anymore.
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }
}
