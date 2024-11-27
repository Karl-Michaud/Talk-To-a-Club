package use_case.club_update_desc;

/**
 * Input Boundary for actions updating a club's description.
 */
public interface ClubUpdateDescInputBoundary {

    /**
     * Executes the update club description use case.
     * @param descUpdateInputData the input data
     */
    void execute(ClubUpdateDescInputData descUpdateInputData);
}
