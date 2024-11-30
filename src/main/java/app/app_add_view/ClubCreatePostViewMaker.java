package app.app_add_view;

import interface_adapter.club_logged_in.club_create_post.ClubCreatePostViewModel;
import view.ClubCreatePostView;

/**
 * Class makes the ClubCreatePost view.
 */
public class ClubCreatePostViewMaker {
    private final ClubCreatePostView clubCreatePostView;
    private final ClubCreatePostViewModel clubCreatePostViewModel;

    public ClubCreatePostViewMaker() {
        clubCreatePostViewModel = new ClubCreatePostViewModel();
        clubCreatePostView = new ClubCreatePostView(clubCreatePostViewModel);
    }

    public ClubCreatePostView getClubCreatePostView() {
        return clubCreatePostView;
    }

    public ClubCreatePostViewModel getClubCreatePostViewModel() {
        return clubCreatePostViewModel;
    }
}
