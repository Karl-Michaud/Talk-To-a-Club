package use_case.club_remove_member;

/**
 * Club remove member use case input boundary.
 */
public interface ClubRemoveMemberInputBoundary {
    /**
     * Executes the club remove member use case.
     * @param clubRemoveMemberInputData the input data.
     */
    void execute(ClubRemoveMemberInputData clubRemoveMemberInputData);
}
