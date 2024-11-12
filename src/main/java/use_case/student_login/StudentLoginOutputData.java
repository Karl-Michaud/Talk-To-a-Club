package use_case.student_login;

import java.util.Map;

import entity.user.Club;

/**
 * The output data for the login use case for students.
 */
public class StudentLoginOutputData {
    private final String username;
    private final Map<Integer, Club> joinedClubs;
    private final boolean useCaseFailed;

    public StudentLoginOutputData(String username, Map<Integer, Club> joinedClubs, boolean useCaseFailed) {
        this.username = username;
        this.joinedClubs = joinedClubs;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public Map<Integer, Club> getJoinedClubs() {
        return joinedClubs;
    }

    /**
     * Returns a value that tells us if the use case failed or not.
     * @return a boolean value true if the use case failed, else false
     */
    public boolean useCaseFailed() {
        return useCaseFailed;
    }
}
