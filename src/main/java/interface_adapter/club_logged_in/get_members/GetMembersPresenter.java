package interface_adapter.club_logged_in.get_members;

import entity.data_structure.DataStore;
import entity.user.Student;
import interface_adapter.ViewManagerModel;
import use_case.club_get_members.ClubGetMembersOutputBoundary;
import use_case.club_get_members.ClubGetMembersOutputData;

/**
 * The presenter for the get members use case for clubs.
 */
public class GetMembersPresenter implements ClubGetMembersOutputBoundary {
    private final GetMembersViewModel getMembersViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetMembersPresenter(GetMembersViewModel getMembersViewModel,
                               ViewManagerModel viewManagerModel) {
        this.getMembersViewModel = getMembersViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ClubGetMembersOutputData data) {
        final String email = data.getEmail();
        final DataStore<Student> members = data.getMembers();

        final GetMembersState currentState = getMembersViewModel.getState();
        currentState.setMembers(members);
        currentState.setClubEmail(email);
        getMembersViewModel.setState(currentState);
        getMembersViewModel.firePropertyChanged("get members");

        viewManagerModel.setState(getMembersViewModel.getViewName());
        viewManagerModel.firePropertyChanged("get members");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final GetMembersState currentState = getMembersViewModel.getState();
        currentState.setErrorMessage(errorMessage);
    }
}
