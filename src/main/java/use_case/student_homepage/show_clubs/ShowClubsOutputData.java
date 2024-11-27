package use_case.student_homepage.show_clubs;

import java.util.List;

/**
 * Class to store the output data for the clubs on the student home view.
 */
public class ShowClubsOutputData {
    private final List<String> clubNames;
    private final String currStudent;

    public ShowClubsOutputData(List<String> clubs, String currStudent) {
        this.clubNames = clubs;
        this.currStudent = currStudent;
    }

    public List<String> getClubs() {
        return clubNames;
    }

    public String getCurrStudent() {
        return currStudent;
    }
}
