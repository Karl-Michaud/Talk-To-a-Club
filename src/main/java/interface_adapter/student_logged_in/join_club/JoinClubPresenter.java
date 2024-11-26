package interface_adapter.student_logged_in.join_club;

import use_case.student_join_club.StudentJoinClubOutputBoundary;
import use_case.student_join_club.StudentJoinClubOutputData;

/**
 * Presenter for the Join Club Use Case.
 */
public class JoinClubPresenter implements StudentJoinClubOutputBoundary {
    private final JoinClubViewModel viewModel;

    public JoinClubPresenter(JoinClubViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @Override
    public void prepareSuccessView(StudentJoinClubOutputData data) {
        final JoinClubState state = viewModel.getState();
        state.setSuccessMessage("Successfully joined the club!");
        state.setErrorMessage(null);
        state.setMember(true);
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final JoinClubState state = viewModel.getState();
        state.setErrorMessage(errorMessage);
        state.setSuccessMessage(null);
        // Membership status remains unchanged on failure
        viewModel.setState(state);
        viewModel.firePropertyChanged();
    }
}
