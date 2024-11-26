package interface_adapter.club_logged_in;

import interface_adapter.ViewModel;

/**
 * The view model for when a club is logged in. This holds all the data for the club when it is logged in.
 */
public class ClubLoggedInViewModel extends ViewModel<ClubLoggedInState> {
    public ClubLoggedInViewModel() {
        super("club logged in");
        this.setState(new ClubLoggedInState());
    }
}
