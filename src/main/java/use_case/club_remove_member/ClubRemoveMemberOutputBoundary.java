package use_case.club_remove_member;

/**
 * Output boundary for the club remove member use case.
 */
public interface ClubRemoveMemberOutputBoundary {
    /**
     * Prepares the success view for the club remove member use case.
     * @param data the output data
     */
    void prepareSuccessView(ClubRemoveMemberOutputData data);

    /**
     * Prepares the failure view for club remove member use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
