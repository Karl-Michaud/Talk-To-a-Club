package use_case.explore_clubs;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for the get clubs use case.
 */
public class ExploreClubsInteractor implements ExploreClubsInputBoundary {
    private final StudentExploreClubsDataAccessInterface studentExploreClubsDataAccessInterface;
    private final ClubExploreClubsDataAccessInterface clubExploreClubsDataAccessInterface;
    private final ExploreClubsOutputBoundary getClubsPresenter;

    public ExploreClubsInteractor(StudentExploreClubsDataAccessInterface studentExploreClubsDataAccessInterface,
                                  ExploreClubsOutputBoundary getMembersPresenter,
                                  ClubExploreClubsDataAccessInterface clubExploreClubsDataAccessInterface) {
        this.studentExploreClubsDataAccessInterface = studentExploreClubsDataAccessInterface;
        this.clubExploreClubsDataAccessInterface = clubExploreClubsDataAccessInterface;
        this.getClubsPresenter = getMembersPresenter;
    }

    /**
     * Executes the get clubs use case.
     * @param inputData the input data.
     */
    @Override
    public void execute(ExploreClubsInputData inputData) {
        final String email = inputData.getEmail();
        if (!studentExploreClubsDataAccessInterface.existsByEmailStudent(email)) {
            getClubsPresenter.prepareFailView(email + ": Account does not exist.");
        }
        else {
            // gets the joined clubs by retrieving user from database and getting joined clubs.
            final Student student = studentExploreClubsDataAccessInterface.getStudent(email);
            final DataStore<Club> joinedClubs = student.getJoinedClubs();

            // gets all the clubs from the database.
            final DataStore<Club> allClubs = clubExploreClubsDataAccessInterface.getAllClubs();

            // all values of all clubs minus the joined ones
            final DataStore<Club> complement = allClubs.complement(joinedClubs);

            final ExploreClubsOutputData outputData = new ExploreClubsOutputData(inputData.getEmail(),
                    complement, false, student);
            getClubsPresenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToClubView(Club club) {
        getClubsPresenter.switchToClubView(club);
    }

    @Override
    public void switchToHomeView() {
        getClubsPresenter.switchToHomeView();
    }
}
