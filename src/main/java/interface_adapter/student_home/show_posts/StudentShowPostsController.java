package interface_adapter.student_home.show_posts;

import use_case.student_homepage.show_posts.StudentShowPostsInputBoundary;
import use_case.student_homepage.show_posts.StudentShowPostsInputData;

/**
 * Controller for the show posts usecase.
 */
public class StudentShowPostsController {
    private final StudentShowPostsInputBoundary showPostsInteractor;

    public StudentShowPostsController(StudentShowPostsInputBoundary showPostsInteractor) {
        this.showPostsInteractor = showPostsInteractor;
    }

    /**
     * Populates the posts panel on the homepage view.
     * @param currentUser the email of the current user.
     */
    public void execute(String currentUser) {
        final StudentShowPostsInputData inputData = new StudentShowPostsInputData(currentUser);
        showPostsInteractor.execute(inputData);
    }
}
