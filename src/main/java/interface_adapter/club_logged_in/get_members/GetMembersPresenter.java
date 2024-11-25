package interface_adapter.club_logged_in.get_members;

import interface_adapter.ViewModel;
import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import use_case.club_get_members.ClubGetMembersOutputBoundary;

/**
 * The presenter for the get members use case for clubs.
 */
public class GetMembersPresenter implements ClubGetMembersOutputBoundary {
    private final GetMembersViewModel getMembersViewModel;
    private final ClubLoggedInViewModel clubLoggedInViewModel;
    private final ViewModel viewModel;

    public GetMembersPresenter(GetMembersViewModel getMembersViewModel, ClubLoggedInViewModel clubLoggedInViewModel,
                               ViewModel model) {
        this.getMembersViewModel = getMembersViewModel;
        this.clubLoggedInViewModel = clubLoggedInViewModel;
        this.viewModel = model;
    }
}
