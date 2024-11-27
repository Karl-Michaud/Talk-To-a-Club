package use_case.student_join_club;

/**
 * Output data for join club use case.
 */
public class StudentJoinClubOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public StudentJoinClubOutputData(String username, boolean useCaseFailed) {
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
