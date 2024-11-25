package interface_adapter.club_logged_in.remove_member;

import interface_adapter.ViewModel;

/**
 * The view model for the remove student use case for clubs.
 */
public class RemoveMemberViewModel extends ViewModel<RemoveMemberState> {
    public RemoveMemberViewModel() {
        super("remove member");
        this.setState(new RemoveMemberState());
    }
}
