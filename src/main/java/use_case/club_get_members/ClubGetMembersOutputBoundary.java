package use_case.club_get_members;

/**
 * Output boundary for the get members use case.
 */
public interface ClubGetMembersOutputBoundary {
    /**
     * Prepares the success view for the get members Use Case for students.
     * @param data the output data
     */
    void prepareSuccessView(ClubGetMembersOutputData data);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
