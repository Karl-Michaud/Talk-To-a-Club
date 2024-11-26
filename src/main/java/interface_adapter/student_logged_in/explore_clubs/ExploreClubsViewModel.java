package interface_adapter.student_logged_in.explore_clubs;

import interface_adapter.ViewModel;

/**
 * Create Post ViewModel for explore clubs view.
 */
public class ExploreClubsViewModel extends ViewModel<ExploreClubsState> {
    public ExploreClubsViewModel() {
        super("explore clubs");
        setState(new ExploreClubsState());
    }
}
