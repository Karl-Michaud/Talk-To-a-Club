package interface_adapter.student_home.show_clubs;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the clubs panel on the student home view.
 */
public class ShowClubsViewModel extends ViewModel<ShowClubsState> {
    public ShowClubsViewModel() {
        super("clubs panel");
        setState(new ShowClubsState());
    }
}
