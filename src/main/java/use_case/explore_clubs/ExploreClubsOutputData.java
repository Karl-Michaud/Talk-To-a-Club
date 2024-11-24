package use_case.explore_clubs;

import entity.data_structure.DataStore;
import entity.user.Club;

/**
 * Output data for the get clubs use case.
 */
public class ExploreClubsOutputData {
    private final String email;
    private final DataStore<Club> notJoinedClubs;
    private final boolean useCaseFailed;

    public ExploreClubsOutputData(String email, DataStore<Club> clubs, boolean useCaseFailed) {
        this.email = email;
        this.notJoinedClubs = clubs;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }

    public DataStore<Club> getNotJoinedClubs() {
        return notJoinedClubs;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
