package interface_adapter.club_create_post;

import use_case.club_create_post.ClubCreatePostInputBoundary;
import use_case.club_create_post.ClubCreatePostInputData;

/**
 * Controller for the login use case.
 */
public class ClubCreatePostController {
    private final ClubCreatePostInputBoundary createPostInteractor;

    public ClubCreatePostController(ClubCreatePostInputBoundary createPostInteractor) {
        this.createPostInteractor = createPostInteractor;
    }

    /**
     * Executes the club create post use case.
     * @param email of the club crating the post
     * @param title of the post
     * @param content of the post
     */
    public void execute(String email, String title, String content) {
        final ClubCreatePostInputData createPostInputData = new ClubCreatePostInputData(email, title, content);
        createPostInteractor.execute(createPostInputData);
    }

    /**
     * Executes the Switch to Create Post View Use Case.
     */
    public void switchToCreatePostView() {
        createPostInteractor.switchToCreatePostView();
    }

    /**
     * Executes the Switch to Club Logged In View Use Case.
     */
    public void switchToClubLoggedInView() {
        createPostInteractor.switchToClubLoggedInView();
    }
}
