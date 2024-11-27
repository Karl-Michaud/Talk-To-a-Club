package interface_adapter.student_logged_in.explore_clubs;

import java.util.ArrayList;
import java.util.Map;

/**
 * State for Explore clubs use case.
 */
public class ExploreClubsState {
    private String error;
    private String clubEmail;
    private String studentEmail;
    private ArrayList<Map<String, String>> clubValues = new ArrayList<>();

    public void setClubValues(ArrayList<Map<String, String>> clubValues) {
        this.clubValues = clubValues;
    }

    public ArrayList<Map<String, String>> getClubValues() {
        return clubValues;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }

    public String getClubEmail() {
        return clubEmail;
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
