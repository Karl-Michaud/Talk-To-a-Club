package interface_adapter.student_home.join_club;

import interface_adapter.student_explore_clubs.ExploreClubsState;
import interface_adapter.student_explore_clubs.ExploreClubsViewModel;
import use_case.student_join_club.StudentJoinClubOutputBoundary;
import use_case.student_join_club.StudentJoinClubOutputData;

/**
 * Presenter for the Join Club Use Case.
 */
public class JoinClubPresenter implements StudentJoinClubOutputBoundary {
    private final ExploreClubsViewModel viewModel;

    public JoinClubPresenter(ExploreClubsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(StudentJoinClubOutputData data) {
        final ExploreClubsState state = viewModel.getState();
        state.getJoinedClubEmails().add(data.getClubEmail());
        viewModel.setState(state);
        // Tells the view to update the club's information
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.firePropertyChanged("fail join");
    }
}
