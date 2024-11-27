package use_case.student_leave_club;

/**
 * Output boundary for leave club use case.
 */
public interface StudentLeaveClubOutputBoundary {
    /**
     * Prepares the success view for the leave club use case.
     * @param data the output data
     */
    void prepareSuccessView(StudentLeaveClubOutputData data);

    /**
     * Prepares the failure view for leave club use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
