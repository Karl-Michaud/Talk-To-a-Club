package use_case.explore_clubs;

import java.util.ArrayList;
import java.util.Map;

import entity.user.Student;

/**
 * Output data for the explore clubs use case.
 */
public class ExploreClubsOutputData {
    private final String email;
    private final ArrayList<Map<String, String>> notJoinedClubs;
    private final boolean useCaseFailed;
    private final Student student;

    public ExploreClubsOutputData(String email, ArrayList<Map<String, String>> clubs, boolean useCaseFailed,
                                  Student student) {
        this.email = email;
        this.notJoinedClubs = clubs;
        this.useCaseFailed = useCaseFailed;
        this.student = student;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Map<String, String>> getNotJoinedClubs() {
        return notJoinedClubs;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public Student getStudent() {
        return student;
    }
}
