package interface_adapter.club_logged_in.remove_member;

import interface_adapter.ViewManagerModel;
import use_case.club_remove_member.ClubRemoveMemberOutputBoundary;
import use_case.club_remove_member.ClubRemoveMemberOutputData;

/**
 * The presenter for the remove member from club use case.
 */
public class RemoveMemberPresenter implements ClubRemoveMemberOutputBoundary {
    private final RemoveMemberViewModel removeMemberViewModel;
    private final ViewManagerModel viewManagerModel;

    public RemoveMemberPresenter(RemoveMemberViewModel removeMemberViewModel, ViewManagerModel viewManagerModel) {
        this.removeMemberViewModel = removeMemberViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ClubRemoveMemberOutputData outputData) {
        final RemoveMemberState removeMemberState = removeMemberViewModel.getState();
        removeMemberState.setMemberName(outputData.getUsername());

        removeMemberViewModel.setState(removeMemberState);
        removeMemberViewModel.firePropertyChanged("remove member");

        viewManagerModel.setState(removeMemberViewModel.getViewName());
        viewManagerModel.firePropertyChanged("remove member");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final RemoveMemberState removeMemberState = removeMemberViewModel.getState();
        removeMemberState.setErrorMessage(errorMessage);
        removeMemberViewModel.setState(removeMemberState);
        removeMemberViewModel.firePropertyChanged();
    }
}
