package use_case.club_get_members;

import java.util.Map;

import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for the get members use case.
 */
public class ClubGetMembersInteractor {
    private final ClubGetMembersUserDataAccessInterface getMembersDataAccessObject;
    private final ClubGetMembersOutputBoundary getMembersPresenter;

    public ClubGetMembersInteractor(ClubGetMembersUserDataAccessInterface getMembersDataAccessObject,
                                    ClubGetMembersOutputBoundary getMembersPresenter) {
        this.getMembersDataAccessObject = getMembersDataAccessObject;
        this.getMembersPresenter = getMembersPresenter;
    }

    /**
     * Executes the get members use case.
     * @param inputData the input data.
     */
    public void execute(ClubGetMembersInputData inputData) {
        final String email = inputData.getEmail();
        if (!getMembersDataAccessObject.existsByEmail(email)) {
            getMembersPresenter.prepareFailView(email + ": Account does not exist.");
        }
        else {
            final Club club = getMembersDataAccessObject.getClub(email);
            final Map<Integer, Student> members = getMembersDataAccessObject.getMembers(club);

            final ClubGetMembersOutputData outputData = new ClubGetMembersOutputData(inputData.getEmail(), members,
                    false);
            getMembersPresenter.prepareSuccessView(outputData);
        }
    }
}
