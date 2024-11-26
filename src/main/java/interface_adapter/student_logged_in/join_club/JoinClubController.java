package interface_adapter.student_logged_in.join_club;

import use_case.student_join_club.StudentJoinClubInputBoundary;
import use_case.student_join_club.StudentJoinClubInputData;

/**
 * Controller for the Join Club Use Case.
 */
public class JoinClubController {
    private final StudentJoinClubInputBoundary inputBoundary;

    public JoinClubController(StudentJoinClubInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Handles the join club request.
     * @param studentEmail The student's email.
     * @param clubEmail The club's email.
     */
    public void joinClub(String studentEmail, String clubEmail) {
        final StudentJoinClubInputData inputData = new StudentJoinClubInputData(studentEmail, clubEmail);
        inputBoundary.execute(inputData);
    }
}
