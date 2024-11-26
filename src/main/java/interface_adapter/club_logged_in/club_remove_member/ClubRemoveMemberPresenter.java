package interface_adapter.club_logged_in.club_remove_member;

import interface_adapter.ViewManagerModel;
import interface_adapter.club_logged_in.ClubLoggedInState;
import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import use_case.club_remove_member.ClubRemoveMemberOutputBoundary;
import use_case.club_remove_member.ClubRemoveMemberOutputData;

/**
 * The presenter for the remove member from club use case.
 */
public class ClubRemoveMemberPresenter implements ClubRemoveMemberOutputBoundary {
    private final ClubLoggedInViewModel clubLoggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubRemoveMemberPresenter(ClubLoggedInViewModel removeMemberViewModel, ViewManagerModel viewManagerModel) {
        this.clubLoggedInViewModel = removeMemberViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ClubRemoveMemberOutputData outputData) {
        viewManagerModel.firePropertyChanged("member removed");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ClubLoggedInState removeMemberState = clubLoggedInViewModel.getState();
        removeMemberState.setErrorMessage(errorMessage);
        clubLoggedInViewModel.setState(removeMemberState);
        clubLoggedInViewModel.firePropertyChanged();
    }
}
