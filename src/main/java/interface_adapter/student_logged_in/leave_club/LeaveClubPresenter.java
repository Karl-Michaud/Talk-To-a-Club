package interface_adapter.student_logged_in.leave_club;

import use_case.student_leave_club.StudentLeaveClubOutputBoundary;
import use_case.student_leave_club.StudentLeaveClubOutputData;

/**
 * Presenter for the Leave Club Use Case.
 */
public class LeaveClubPresenter implements StudentLeaveClubOutputBoundary {
    private final LeaveClubViewModel viewModel;

    public LeaveClubPresenter(LeaveClubViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(StudentLeaveClubOutputData data) {
        final LeaveClubState state = viewModel.getState();
        state.setErrorMessage(null);
        state.setMember(false);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final LeaveClubState state = viewModel.getState();
        state.setErrorMessage(errorMessage);
        // Membership status remains unchanged on failure
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }
}
