package use_case.student_get_clubs;

import entity.data_structure.DataStore;
import entity.user.Club;

/**
 * Interactor for the get clubs use case.
 */
public class StudentGetClubsInteractor implements StudentGetClubsInputBoundary {
    private final StudentGetClubsDataAccessInterface getClubsDataAccessInterface;
    private final StudentGetClubsOutputBoundary getClubsPresenter;

    public StudentGetClubsInteractor(StudentGetClubsDataAccessInterface getMembersDataAccessObject,
                                     StudentGetClubsOutputBoundary getMembersPresenter) {
        this.getClubsDataAccessInterface = getMembersDataAccessObject;
        this.getClubsPresenter = getMembersPresenter;
    }

    /**
     * Executes the get clubs use case.
     * @param inputData the input data.
     */
    @Override
    public void execute(StudentGetClubsInputData inputData) {
        final String email = inputData.getEmail();
        if (!getClubsDataAccessInterface.existsByEmailStudent(email)) {
            getClubsPresenter.prepareFailView(email + ": Account does not exist.");
        }
        else {
            // gets the joined clubs by retrieving user from database and getting joined clubs.
            final DataStore<Club> joinedClubs = getClubsDataAccessInterface.getStudent(email).getJoinedClubs();

            final StudentGetClubsOutputData outputData = new StudentGetClubsOutputData(inputData.getEmail(),
                    joinedClubs, false);
            getClubsPresenter.prepareSuccessView(outputData);
        }
    }
}
