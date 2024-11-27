package interface_adapter.student_logged_in.leave_club;

import interface_adapter.ViewModel;

/**
 * ViewModel for the Leave Club Use Case.
 */
public class LeaveClubViewModel extends ViewModel<LeaveClubState> {
    public LeaveClubViewModel() {
        super("LeaveClubView");
        setState(new LeaveClubState());
    }
}
