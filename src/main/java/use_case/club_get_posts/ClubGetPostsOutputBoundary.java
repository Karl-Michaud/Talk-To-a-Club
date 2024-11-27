package use_case.club_get_posts;

/**
 * The output Boundary for the Club Get Posts Use Case.
 */
public interface ClubGetPostsOutputBoundary {

    /**
     * Prepares to display the club's posts for the Club Home View. Also prepares a success message.
     * @param outputData the output data
     */
    void prepareDisplayPosts(ClubGetPostsOutputData outputData);

    /**
     * Prepares the fail message for the Club Home View.
     * @param outputData the output data
     */
    void prepareFailMessage(ClubGetPostsOutputData outputData);
}
