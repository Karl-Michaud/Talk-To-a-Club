package use_case.explore_clubs;

/**
 * Input boundary interface for the explore clubs use case.
 */
public interface ExploreClubsInputBoundary {

    /**
     * Executes the get clubs use case.
     * @param exploreClubsInputData the input data.
     */
    void execute(ExploreClubsInputData exploreClubsInputData);
}
