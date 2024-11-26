package interface_adapter.club_get_posts;

import use_case.club_get_posts.ClubGetPostsInputBoundary;
import use_case.club_get_posts.ClubGetPostsInputData;

/**
 * Controller for the Club Get Posts Use Case.
 */
public class ClubGetPostsController {

    private final ClubGetPostsInputBoundary clubGetPostsInteractor;

    public ClubGetPostsController(ClubGetPostsInputBoundary clubGetPostsInteractor) {
        this.clubGetPostsInteractor = clubGetPostsInteractor;
    }

    /**
     * Executes the Club Get Posts Use Case.
     * @param email the email of the logged in club
     */
    public void execute(String email) {
        final ClubGetPostsInputData clubRefreshData = new ClubGetPostsInputData(email);

        clubGetPostsInteractor.execute(clubRefreshData);
    }
}
