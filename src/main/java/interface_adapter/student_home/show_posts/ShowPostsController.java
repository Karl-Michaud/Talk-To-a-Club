package interface_adapter.student_home.show_posts;

import use_case.student_homepage.show_posts.ShowPostsInputBoundary;
import use_case.student_homepage.show_posts.ShowPostsInputData;

/**
 * Controller for the show posts usecase.
 */
public class ShowPostsController {
    private final ShowPostsInputBoundary showPostsInteractor;

    public ShowPostsController(ShowPostsInputBoundary showPostsInteractor) {
        this.showPostsInteractor = showPostsInteractor;
    }

    /**
     * Populates the posts panel on the homepage view.
     * @param currentUser the email of the current user.
     */
    public void execute(String currentUser) {
        final ShowPostsInputData inputData = new ShowPostsInputData(currentUser);
        showPostsInteractor.execute(inputData);
    }
}
