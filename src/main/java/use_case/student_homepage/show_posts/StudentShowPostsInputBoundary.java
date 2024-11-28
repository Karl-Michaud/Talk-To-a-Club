package use_case.student_homepage.show_posts;

/**
 * InputBoundary for show posts usecase.
 */
public interface StudentShowPostsInputBoundary {
    /**
     * Executes the show posts use case.
     * @param studentShowPostsInputData the input data.
     */
    void execute(StudentShowPostsInputData studentShowPostsInputData);
}
