package use_case.student_homepage.show_posts;

/**
 * Interface for the output boundary of the posts panel in StudentHome.
 */
public interface ShowPostsOutputBoundary {

    /**
     * Gathers all the posts to prepare to be viewed in the StudentHomeView.
     * @param showPostsOutputData all the posts.
     */
    void preparePostContent(ShowPostsOutputData showPostsOutputData);

    /**
     * Gives the presenter an error to present in case something goes wrong.
     * @param errorMessage an error message.
     */
    void prepareFailView(String errorMessage);
}
