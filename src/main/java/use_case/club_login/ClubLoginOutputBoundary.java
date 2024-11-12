package use_case.club_login;

/**
 * Output boundary for the login use case for clubs.
 */
public interface ClubLoginOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case for Clubs.
     * @param clubLoginOutputData the output data
     */
    void prepareSuccessView(ClubLoginOutputData clubLoginOutputData);

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
