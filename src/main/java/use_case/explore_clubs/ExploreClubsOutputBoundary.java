package use_case.explore_clubs;

import java.util.Map;

/**
 * Output boundary for the get clubs use case.
 */
public interface ExploreClubsOutputBoundary {
    /**
     * Prepares the success view for the explore clubs Use Case for students.
     * @param data the output data
     */
    void prepareSuccessView(ExploreClubsOutputData data);

    /**
     * Prepares the failure view for the explore clubs use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Prepares the club view for the explore clubs use case.
     * @param club is the club needed to switch to.
     */
    void switchToClubView(Map<String, String> club);

    /**
     * Prepares the home view for the explore clubs use case.
     */
    void switchToHomeView();

}
