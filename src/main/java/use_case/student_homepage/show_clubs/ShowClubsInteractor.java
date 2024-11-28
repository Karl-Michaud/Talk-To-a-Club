package use_case.student_homepage.show_clubs;

import java.util.ArrayList;

import entity.data_structure.DataStoreArrays;
import entity.user.Club;

/**
 * Interactor for the show clubs usecase.
 */
public class ShowClubsInteractor implements ShowClubsInputBoundary {
    private final ShowClubsOutputBoundary showClubsPresenter;
    private final ShowClubsAccessInterface showClubsAccessInterface;

    public ShowClubsInteractor(ShowClubsOutputBoundary showClubsPresenter,
                               ShowClubsAccessInterface showClubsAccessInterface) {
        this.showClubsPresenter = showClubsPresenter;
        this.showClubsAccessInterface = showClubsAccessInterface;
    }

    @Override
    public void execute(ShowClubsInputData showClubsInputData) {
        final String currUserEmail = showClubsInputData.getUserEmail();
        if (!showClubsAccessInterface.existsByEmailStudent(currUserEmail)) {
            showClubsPresenter.prepareFailView("The account does not exist.");
        }
        else {
            final DataStoreArrays<Club> clubs = (DataStoreArrays<Club>) showClubsAccessInterface.getStudent(
                    currUserEmail).getJoinedClubs();
            final ArrayList<String> clubNames = new ArrayList<>();
            for (final Club club : clubs) {
                clubNames.add(club.getUsername());
            }
            final ShowClubsOutputData showClubsOutputData = new ShowClubsOutputData(clubNames, currUserEmail);
            showClubsPresenter.preparePostContent(showClubsOutputData);
        }
    }
}
