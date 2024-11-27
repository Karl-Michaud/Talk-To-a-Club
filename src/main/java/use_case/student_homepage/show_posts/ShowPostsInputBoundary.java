package use_case.student_homepage.show_posts;

/**
 * InputBoundary for show posts usecase.
 */
public interface ShowPostsInputBoundary {
    /**
     * Executes the show posts use case.
     * @param showPostsInputData the input data.
     */
    void execute(ShowPostsInputData showPostsInputData);
}
