package interface_adapter.club_logged_in.get_members;

import interface_adapter.ViewModel;

/**
 * View model for the get members use case for clubs.
 */
public class GetMembersViewModel extends ViewModel<GetMembersState> {
    public GetMembersViewModel() {
        super("get members");
        this.setState(new GetMembersState());
    }
}
