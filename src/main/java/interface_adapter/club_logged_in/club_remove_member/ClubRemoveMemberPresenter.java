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
        final String removedMemberName = outputData.getUsername();

        // We will remove the member manually from the state.
        // Start by getting the state.
        final ClubLoggedInState currentState = clubLoggedInViewModel.getState();

        // Find index of the removed member in array list of the current state.
        final int index = currentState.getMembersName().indexOf(removedMemberName);

        // Use index to get member email to remove
        final String removedMemberEmail = currentState.getMembersEmail().get(index);

        // Remove the member from both lists. This will update the state due to aliasing. (Check implementation
        // if any doubts).
        currentState.getMembersName().remove(removedMemberName);
        currentState.getMembersEmail().remove(removedMemberEmail);

        // Save and fire property changed to club logged in state
        clubLoggedInViewModel.setState(currentState);
        clubLoggedInViewModel.firePropertyChanged("member removed");

        viewManagerModel.setState(clubLoggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged("member removed");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ClubLoggedInState removeMemberState = clubLoggedInViewModel.getState();
        removeMemberState.setMessage(errorMessage);
        clubLoggedInViewModel.setState(removeMemberState);
        clubLoggedInViewModel.firePropertyChanged();
    }
}
