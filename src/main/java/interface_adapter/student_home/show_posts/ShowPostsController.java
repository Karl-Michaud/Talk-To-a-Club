package interface_adapter.student_home.show_posts;

import use_case.student_homepage.show_posts.ShowPostsInputBoundary;

/**
 * Controller for the show posts panel/ usecase.
 */
public class ShowPostsController {
    private final ShowPostsInputBoundary showPostsInteractor;

    public ShowPostsController(ShowPostsInputBoundary showPostsInteractor) {
        this.showPostsInteractor = showPostsInteractor;
    }
}
