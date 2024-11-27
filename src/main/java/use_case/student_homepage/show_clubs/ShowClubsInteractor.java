package use_case.student_homepage.show_clubs;

import java.util.ArrayList;
import java.util.List;

import entity.data_structure.DataStoreArrays;
import entity.user.Club;
import entity.user.Student;

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
        final String currUser = showClubsInputData.getUser();
        final Student currStudent = showClubsAccessInterface.getStudent(currUser);
        final DataStoreArrays<Club> joinedClubs = (DataStoreArrays<Club>) currStudent.getJoinedClubs();
        final List<String> joinedClubNames = new ArrayList<>();
        for (final Club club : joinedClubs) {
            joinedClubNames.add(club.getUsername());
        }
        final ShowClubsOutputData showClubsOutputData = new ShowClubsOutputData(joinedClubNames, currUser);
        showClubsPresenter.preparePostContent(showClubsOutputData);

    }
}
