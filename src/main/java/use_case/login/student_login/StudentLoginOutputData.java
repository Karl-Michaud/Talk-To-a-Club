package use_case.login.student_login;

import entity.data_structure.DataStore;
import entity.user.Club;

/**
 * The output data for the login use case for students.
 */
public class StudentLoginOutputData {
    private final String username;
    private final String email;
    private final DataStore<Club> joinedClubs;
    private final boolean useCaseFailed;

    public StudentLoginOutputData(String username, String email, DataStore<Club> joinedClubs, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.joinedClubs = joinedClubs;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public DataStore<Club> getJoinedClubs() {
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
