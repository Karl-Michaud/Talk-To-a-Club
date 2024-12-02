package use_case.student_show_clubs;

import java.util.List;
import java.util.Map;

/**
 * Class to store the output data for the clubs on the student home view.
 */
public class StudentShowClubsOutputData {
    private final List<Map<String, String>> followedClubs;
    private final String currStudentEmail;

    public StudentShowClubsOutputData(List<Map<String, String>> clubs, String currStudent) {
        this.followedClubs = clubs;
        this.currStudentEmail = currStudent;
    }

    public List<Map<String, String>> getClubs() {
        return followedClubs;
    }

    public String getCurrStudentEmail() {
        return currStudentEmail;
    }
}
