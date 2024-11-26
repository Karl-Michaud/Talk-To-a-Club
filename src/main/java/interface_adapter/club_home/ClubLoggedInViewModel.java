package interface_adapter.club_home;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Club Home View.
 */
public class ClubLoggedInViewModel extends ViewModel<ClubHomeState> {

    public ClubLoggedInViewModel() {
        super("club home");
        setState(new ClubHomeState());
    }
}
