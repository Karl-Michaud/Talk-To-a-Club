package use_case.club_create_post;

/**
 * Input boundary for creating posts use case.
 */
public interface ClubCreatePostInputBoundary {

    /**
     * Executes the club post use case.
     * @param clubCreatePostInputData the input data
     */
    void execute(ClubCreatePostInputData clubCreatePostInputData);

    /**
     * Executes the switch to create post view use case.
     */
    void switchToCreatePostView();

    /**
     * Executes the switch to club logged in view use case.
     */
    void switchToClubLoggedInView();
}
