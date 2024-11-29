package use_case.club_remove_member;

/**
 * Output Boundary for club remove member use case.
 */
public class ClubRemoveMemberOutputData {
    private final String studentUsername;
    private final String studentEmail;
    private final boolean useCaseFailed;

    public ClubRemoveMemberOutputData(String studentEmail,
                                      String studentUsername, boolean useCaseFailed) {
        this.studentUsername = studentUsername;
        this.studentEmail = studentEmail;
        this.useCaseFailed = useCaseFailed;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
