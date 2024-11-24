package use_case.student_get_clubs;

import entity.data_structure.DataStore;
import entity.user.Club;

/**
 * Output data for the get clubs use case.
 */
public class StudentGetClubsOutputData {
    private final String email;
    private final DataStore<Club> joinedClubs;
    private final boolean useCaseFailed;

    public StudentGetClubsOutputData(String email, DataStore<Club> members, boolean useCaseFailed) {
        this.email = email;
        this.joinedClubs = members;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }

    public DataStore<Club> getJoinedClubs() {
        return joinedClubs;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
