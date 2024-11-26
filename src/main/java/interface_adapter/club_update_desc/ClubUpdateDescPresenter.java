package interface_adapter.club_update_desc;

import interface_adapter.ViewManagerModel;
import use_case.club_update_desc.ClubUpdateDescOutputBoundary;
import use_case.club_update_desc.ClubUpdateDescOutputData;

/**
 * The presenter for the Club Update Description Use Case.
 */
public class ClubUpdateDescPresenter implements ClubUpdateDescOutputBoundary {

    private final ClubLoggedInViewModel clubLoggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubUpdateDescController(ClubLoggedInViewModel clubLoggedInViewModel, ViewManagerModel viewManagerModel) {
        this.clubLoggedInViewModel = clubLoggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareMessage(ClubUpdateDescOutputData outputData) {
        // Get the state of the current ClubLoggedInViewModel and set the message to the new one
        final ClubLoggedInState clubLoggedInState = clubLoggedInViewModel.getState();
        clubLoggedInState.setMessage(outputData.getMessage());
        clubLoggedInViewModel.setState(clubLoggedInState);

        // Fires property change to the viewManagerModel
        viewManagerModel.setState(clubLoggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
