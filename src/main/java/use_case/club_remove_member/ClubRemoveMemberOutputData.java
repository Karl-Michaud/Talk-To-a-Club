package use_case.club_remove_member;

/**
 * Output Boundary for club remove member use case.
 */
public class ClubRemoveMemberOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public ClubRemoveMemberOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

}
