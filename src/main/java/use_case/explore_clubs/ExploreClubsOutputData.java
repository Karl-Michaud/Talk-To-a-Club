package use_case.explore_clubs;

import java.util.ArrayList;
import java.util.Map;

/**
 * Output data for the explore clubs use case.
 */
public class ExploreClubsOutputData {
    private final String clubEmail;
    private final ArrayList<Map<String, String>> notJoinedClubs;
    private final boolean useCaseFailed;
    private final String studentEmail;
    private final ArrayList<String> joinedClubEmails;

    public ExploreClubsOutputData(String email, ArrayList<Map<String, String>> clubs, boolean useCaseFailed,
                                  String studentEmail, ArrayList<String> joinedClubsEmails) {
        this.clubEmail = email;
        this.notJoinedClubs = clubs;
        this.useCaseFailed = useCaseFailed;
        this.studentEmail = studentEmail;
        this.joinedClubEmails = joinedClubsEmails;
    }

    public String getClubEmail() {
        return clubEmail;
    }

    public ArrayList<Map<String, String>> getNotJoinedClubs() {
        return notJoinedClubs;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public ArrayList<String> getJoinedClubsEmails() {
        return joinedClubEmails; }
}
