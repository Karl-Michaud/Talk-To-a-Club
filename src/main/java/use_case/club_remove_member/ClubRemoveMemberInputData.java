package use_case.club_remove_member;

/**
 * Input data for the club remove member use case.
 */
public class ClubRemoveMemberInputData {
    private final String studentEmail;
    private final String clubEmail;

    public ClubRemoveMemberInputData(String studentEmail, String clubEmail) {
        this.studentEmail = studentEmail;
        this.clubEmail = clubEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getClubEmail() {
        return clubEmail;
    }
}
