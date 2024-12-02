package use_case.student_show_posts;

/**
 * Interface for the output boundary of the posts panel in StudentHome.
 */
public interface StudentShowPostsOutputBoundary {

    /**
     * Gathers all the posts to prepare to be viewed in the StudentHomeView.
     * @param studentShowPostsOutputData all the posts.
     */
    void preparePostContent(StudentShowPostsOutputData studentShowPostsOutputData);

    /**
     * Gives the presenter an error to present in case something goes wrong.
     * @param errorMessage an error message.
     */
    void prepareFailView(String errorMessage);
}
