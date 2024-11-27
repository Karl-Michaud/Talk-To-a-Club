package interface_adapter.club_logged_in;

import java.util.ArrayList;

/**
 * Club Logged In state.
 */
public class ClubLoggedInState {
    private String clubName = "";

    private String email = "";

    private String password = "";
    private String errorMessage;

    private ArrayList<String> membersEmail;
    private ArrayList<String> membersName;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ArrayList<String> getMembersEmail() {
        return membersEmail;
    }

    public void setMembersEmail(ArrayList<String> membersEmail) {
        this.membersEmail = membersEmail;
    }

    public ArrayList<String> getMembersName() {
        return membersName;
    }

    public void setMembersName(ArrayList<String> membersName) {
        this.membersName = membersName;
    }
}
