package use_case.student_homepage.show_clubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            final ArrayList<Map<String, String>> clubData = new ArrayList<>();
            for (final Club club : clubs) {
                final Map<String, String> singleClubData = new HashMap<>();
                singleClubData.put("username", club.getUsername());
                singleClubData.put("email", club.getEmail());
                singleClubData.put("description", club.getClubDescription());
                clubData.add(singleClubData);
            }
            final ShowClubsOutputData showClubsOutputData = new ShowClubsOutputData(clubData, currUserEmail);
            showClubsPresenter.preparePostContent(showClubsOutputData);
        }
    }
}
