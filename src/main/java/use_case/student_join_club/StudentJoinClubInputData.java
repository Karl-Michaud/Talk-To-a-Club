package use_case.student_join_club;

/**
 * Input data for join club use case.
 */
public class StudentJoinClubInputData {
    private final String studentEmail;
    private final String clubEmail;

    public StudentJoinClubInputData(String studentEmail, String clubEmail) {
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
