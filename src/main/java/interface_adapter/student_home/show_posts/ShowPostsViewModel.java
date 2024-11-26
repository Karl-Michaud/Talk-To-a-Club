package interface_adapter.student_home.show_posts;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the current posts panel on the student home view.
 */
public class ShowPostsViewModel extends ViewModel<ShowPostsState> {
    public ShowPostsViewModel() {
        super("posts panel");
        setState(new ShowPostsState());
    }
}
