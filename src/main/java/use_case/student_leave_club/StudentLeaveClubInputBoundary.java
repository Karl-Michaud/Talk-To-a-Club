package use_case.student_leave_club;

/**
 * Input boundary for leave club use case.
 */
public interface StudentLeaveClubInputBoundary {
    /**
     * Executes the leave club use case.
     * @param studentLeaveClubInputBoundary the input data.
     */
    void execute(StudentLeaveClubInputData studentLeaveClubInputBoundary);
}
