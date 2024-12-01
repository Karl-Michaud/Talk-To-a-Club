package use_case.student_leave_club;

/**
 * Output data for leave club use case.
 */
public class StudentLeaveClubOutputData {
    private final String username;
    private final String clubEmail;
    private final boolean useCaseFailed;

    public StudentLeaveClubOutputData(String username, String clubEmail, boolean useCaseFailed) {
        this.username = username;
        this.clubEmail = clubEmail;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }

    public String getClubEmail() {
        return this.clubEmail;
    }
}
