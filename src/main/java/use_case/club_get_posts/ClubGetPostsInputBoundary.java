package use_case.club_get_posts;

/**
 * Input Boundary for actions getting a club's posts.
 */
public interface ClubGetPostsInputBoundary {

    /**
     * Executes the get club posts use case.
     * @param clubGetPostsInputData the input data
     */
    void execute(ClubGetPostsInputData clubGetPostsInputData);
}
