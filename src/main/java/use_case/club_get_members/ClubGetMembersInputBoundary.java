package use_case.club_get_members;

/**
 * Input boundary interface for the get members use case.
 */
public interface ClubGetMembersInputBoundary {

    /**
     * Executes the get members use case.
     * @param clubGetMembersInputData the input data.
     */
    void execute(ClubGetMembersInputData clubGetMembersInputData);
}
