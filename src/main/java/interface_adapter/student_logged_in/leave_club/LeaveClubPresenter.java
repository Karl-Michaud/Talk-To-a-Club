package interface_adapter.student_logged_in.leave_club;

import interface_adapter.student_logged_in.explore_clubs.ExploreClubsState;
import interface_adapter.student_logged_in.explore_clubs.ExploreClubsViewModel;
import use_case.student_leave_club.StudentLeaveClubOutputBoundary;
import use_case.student_leave_club.StudentLeaveClubOutputData;

/**
 * Presenter for the Leave Club Use Case.
 */
public class LeaveClubPresenter implements StudentLeaveClubOutputBoundary {
    private final ExploreClubsViewModel viewModel;

    public LeaveClubPresenter(ExploreClubsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(StudentLeaveClubOutputData data) {
        final ExploreClubsState state = viewModel.getState();
        state.getJoinedClubEmails().remove(data.getClubEmail());
        viewModel.setState(state);
        // Tells the view to update the club's information
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.firePropertyChanged("fail leave");
    }
}
