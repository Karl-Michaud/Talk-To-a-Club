package interface_adapter.student_explore_clubs;

import java.util.ArrayList;
import java.util.Map;

/**
 * State for Explore clubs use case.
 */
public class ExploreClubsState {
    private String error;
    private String currentClubName;
    private String currentClubEmail;
    private String currentClubDescription;
    private String studentEmail;
    private String currentNumberOfMembersString;
    private ArrayList<Map<String, String>> clubValues = new ArrayList<>();
    private ArrayList<String> joinedClubEmails = new ArrayList<>();

    public String getCurrentClubName() {
        return currentClubName;
    }

    public void setCurrentClubName(String currentClubName) {
        this.currentClubName = currentClubName;
    }

    public String getCurrentClubDescription() {
        return currentClubDescription;
    }

    public void setCurrentClubDescription(String currentClubDescription) {
        this.currentClubDescription = currentClubDescription;
    }

    public String getCurrentNumberOfMembersString() {
        return currentNumberOfMembersString;
    }

    public void setCurrentNumberOfMembersString(String currentNumberOfMembersString) {
        this.currentNumberOfMembersString = currentNumberOfMembersString;
    }

    public void setJoinedClubEmails(ArrayList<String> joinedClubEmails) {
        this.joinedClubEmails = joinedClubEmails;
    }

    public ArrayList<String> getJoinedClubEmails() {
        return joinedClubEmails;
    }

    public void setClubValues(ArrayList<Map<String, String>> clubValues) {
        this.clubValues = clubValues;
    }

    public ArrayList<Map<String, String>> getClubValues() {
        return clubValues;
    }

    public void setCurrentClubEmail(String currentClubEmail) {
        this.currentClubEmail = currentClubEmail;
    }

    public String getCurrentClubEmail() {
        return currentClubEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
