package interface_adapter.club_home;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the Club Home View.
 */
public class ClubHomeViewModel extends ViewModel<ClubHomeState> {

    public ClubHomeViewModel() {
        super("club home");
        setState(new ClubHomeState());
    }
}
