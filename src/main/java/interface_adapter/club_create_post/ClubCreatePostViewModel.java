package interface_adapter.club_create_post;

import interface_adapter.ViewModel;

/**
 * Create Post ViewModel for create post view.
 */
public class ClubCreatePostViewModel extends ViewModel<ClubCreatePostState> {
    public ClubCreatePostViewModel() {
        super("create post");
        setState(new ClubCreatePostState());
    }
}
