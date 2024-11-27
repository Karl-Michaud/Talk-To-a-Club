package interface_adapter.student_home.show_clubs;

import use_case.student_homepage.show_clubs.ShowClubsOutputBoundary;
import use_case.student_homepage.show_clubs.ShowClubsOutputData;

/**
 * The presenter that passes the clubs on to the ViewModel for the StudentHomeView.
 */
public class StudentShowClubsPresenter implements ShowClubsOutputBoundary {

    private final ShowClubsViewModel viewModel;

    public StudentShowClubsPresenter(ShowClubsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void preparePostContent(ShowClubsOutputData showClubsOutputData) {
        final ShowClubsState state = viewModel.getState();
        state.setClubNames(showClubsOutputData.getClubs());
        state.setCurrentUser(showClubsOutputData.getCurrStudent());
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ShowClubsState state = viewModel.getState();
        state.setShowClubsError(errorMessage);
        viewModel.firePropertyChanged();
    }
}
