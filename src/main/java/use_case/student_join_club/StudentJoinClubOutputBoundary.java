package use_case.student_join_club;

/**
 * Output boundary for Join club use case.
 */
public interface StudentJoinClubOutputBoundary {
    /**
     * Prepares the success view for the join club use case.
     * @param data the output data
     */
    void prepareSuccessView(StudentJoinClubOutputData data);

    /**
     * Prepares the failure view for join club use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
