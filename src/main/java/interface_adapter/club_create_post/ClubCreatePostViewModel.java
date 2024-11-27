package interface_adapter.club_create_post;

import interface_adapter.ViewModel;

/**
 * Create Post ViewModel for create post view.
 */
public class ClubCreatePostViewModel extends ViewModel<CreatePostState> {
    public ClubCreatePostViewModel() {
        super("create post");
        setState(new CreatePostState());
    }
}
