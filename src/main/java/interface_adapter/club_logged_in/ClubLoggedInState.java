package interface_adapter.club_logged_in;

import java.util.List;

/**
 * Club Logged In state.
 */
public class ClubLoggedInState {
    private String clubName = "";

    private String email = "";

    private String password = "";
    private String message;

    private String descriptionTextArea = "";

    // Note, a single index of these two lists correspond to one post
    private List<String> postTitles;
    private List<String> postBodies;

    // Note, a single index of these two lists correspond to one member
    private List<String> membersName;
    private List<String> membersEmail;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDescriptionTextArea() {
        return descriptionTextArea;
    }

    public void setDescriptionTextArea(String descriptionTextArea) {
        this.descriptionTextArea = descriptionTextArea;
    }

    public List<String> getPostTitles() {
        return this.postTitles;
    }

    public void setPostTitles(List<String> postTitles) {
        this.postTitles = postTitles;
    }

    public List<String> getPostBodies() {
        return this.postBodies;
    }

    public void setPostBodies(List<String> postBodies) {
        this.postBodies = postBodies;
    }

    public List<String> getMembersEmail() {
        return membersEmail;
    }

    public void setMembersEmail(List<String> membersEmail) {
        this.membersEmail = membersEmail;
    }

    public List<String> getMembersName() {
        return membersName;
    }

    public void setMembersName(List<String> membersName) {
        this.membersName = membersName;
    }
}
