package use_case.student_leave_club;

/**
 * Input data for student leave use case.
 */
public class StudentLeaveClubInputData {
    private final String studentEmail;
    private final String clubEmail;

    public StudentLeaveClubInputData(String studentEmail, String clubEmail) {
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
