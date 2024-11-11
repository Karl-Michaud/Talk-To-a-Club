package use_case.login;

import entity.user.Club;

import java.util.HashMap;
import java.util.Map;

public class ClubLoginOutputData {
    private final String username;
    private final Map<Integer, Club> joinedClubs;
    private final boolean useCaseFailed;

    public ClubLoginOutputData(String username,Map<Integer, Club> joinedClubs ,boolean useCaseFailed) {
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

    public boolean useCaseFailed() {
        return useCaseFailed;
    }
}
