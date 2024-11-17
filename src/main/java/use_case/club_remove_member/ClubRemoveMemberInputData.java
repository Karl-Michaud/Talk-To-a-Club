package use_case.club_remove_member;

/**
 * Input data for the club remove member use case.
 */
public class ClubRemoveMemberInputData {
    private final String studentUsername;
    private final String clubEmail;

    public ClubRemoveMemberInputData(String studentUsername, String clubEmail) {
        this.studentUsername = studentUsername;
        this.clubEmail = clubEmail;
    }

    public String getStudentUsername() {
        return studentUsername;
    }

    public String getClubEmail() {
        return clubEmail;
    }
}
