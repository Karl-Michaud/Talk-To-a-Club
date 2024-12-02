package interface_adapter.student_home.student_leave_club;

import use_case.student_leave_club.StudentLeaveClubInputBoundary;
import use_case.student_leave_club.StudentLeaveClubInputData;

/**
 * Controller for the Leave Club Use Case.
 */
public class LeaveClubController {
    private final StudentLeaveClubInputBoundary inputBoundary;

    public LeaveClubController(StudentLeaveClubInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Handles the leave club request.
     * @param studentEmail The student's email.
     * @param clubEmail The club's email.
     */
    public void leaveClub(String studentEmail, String clubEmail) {
        final StudentLeaveClubInputData inputData = new StudentLeaveClubInputData(studentEmail, clubEmail);
        inputBoundary.execute(inputData);
    }
}
