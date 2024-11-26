package use_case.explore_clubs;

import entity.user.Club;

/**
 * Input boundary interface for the explore clubs use case.
 */
public interface ExploreClubsInputBoundary {

    /**
     * Executes the explore clubs use case.
     * @param exploreClubsInputData the input data.
     */
    void execute(ExploreClubsInputData exploreClubsInputData);

    /**
     * Prepares the club view for the explore clubs use case.
     * @param club is the club needed to switch to.
     */
    void switchToClubView(Club club);

    /**
     * Prepares the home view for the explore clubs use case.
     */
    void switchToHomeView();
}
