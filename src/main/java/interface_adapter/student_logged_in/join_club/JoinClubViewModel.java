package interface_adapter.student_logged_in.join_club;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Join Club Use Case.
 */
public class JoinClubViewModel extends ViewModel<JoinClubState> {
    public JoinClubViewModel() {
        super("JoinClubView");
        setState(new JoinClubState());
    }
}
