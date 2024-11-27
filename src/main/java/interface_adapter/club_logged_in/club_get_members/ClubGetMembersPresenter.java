package interface_adapter.club_logged_in.club_get_members;

import entity.data_structure.DataStore;
import entity.user.Student;
import interface_adapter.ViewManagerModel;
import interface_adapter.club_logged_in.ClubLoggedInState;
import interface_adapter.club_logged_in.ClubLoggedInViewModel;
import use_case.club_get_members.ClubGetMembersOutputBoundary;
import use_case.club_get_members.ClubGetMembersOutputData;

import java.util.ArrayList;

/**
 * The presenter for the get members use case for clubs.
 */
public class ClubGetMembersPresenter implements ClubGetMembersOutputBoundary {
    private final ClubLoggedInViewModel clubLoggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public ClubGetMembersPresenter(ClubLoggedInViewModel getMembersViewModel,
                                   ViewManagerModel viewManagerModel) {
        this.clubLoggedInViewModel = getMembersViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ClubGetMembersOutputData data) {
        final String email = data.getEmail();

        // Get the members of the club
        final ArrayList<String> membersEmail = data.getMembersEmail();
        final ArrayList<String> memebrsName = data.getMembersName();

        final ClubLoggedInState currentState = clubLoggedInViewModel.getState();

        // Set the current members and email to the state
        currentState.setMembersEmail(membersEmail);
        currentState.setMembersName(memebrsName);
        currentState.setEmail(email);

        // Save the state
        clubLoggedInViewModel.setState(currentState);
        clubLoggedInViewModel.firePropertyChanged("get members");

        viewManagerModel.setState(clubLoggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged("get members");
    }

    @Override
    public void prepareFailView(String errorMessage) {
        final ClubLoggedInState currentState = clubLoggedInViewModel.getState();
        currentState.setMessage(errorMessage);
    }
}
