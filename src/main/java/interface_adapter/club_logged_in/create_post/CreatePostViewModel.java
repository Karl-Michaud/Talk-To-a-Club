package interface_adapter.club_logged_in.create_post;

import interface_adapter.ViewModel;

/**
 * Create Post ViewModel for create post view.
 */
public class CreatePostViewModel extends ViewModel<CreatePostState> {
    public CreatePostViewModel() {
        super("create post");
        setState(new CreatePostState());
    }
}
