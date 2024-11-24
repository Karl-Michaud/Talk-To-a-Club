package use_case.club_get_members;

import entity.data_structure.DataStore;
import entity.user.Student;

/**
 * Interactor for the get members use case.
 */
public class ClubGetMembersInteractor implements ClubGetMembersInputBoundary {
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
    @Override
    public void execute(ClubGetMembersInputData inputData) {
        final String email = inputData.getEmail();
        if (!getMembersDataAccessObject.existsByEmailClub(email)) {
            getMembersPresenter.prepareFailView(email + ": Account does not exist.");
        }
        else {
            final DataStore<Student> members = getMembersDataAccessObject.getClub(email).getClubMembers();

            final ClubGetMembersOutputData outputData = new ClubGetMembersOutputData(inputData.getEmail(), members,
                    false);
            getMembersPresenter.prepareSuccessView(outputData);
        }
    }
}
