package use_case.explore_clubs;

import entity.data_structure.DataStore;
import entity.user.Club;

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
            final DataStore<Club> joinedClubs = studentExploreClubsDataAccessInterface
                    .getStudent(email).getJoinedClubs();

            // gets all the clubs from the database.
            final DataStore<Club> allClubs = clubExploreClubsDataAccessInterface.getAllClubs();

            // all values of all clubs minus the joined ones
            final DataStore<Club> complement = allClubs.complement(joinedClubs);

            final ExploreClubsOutputData outputData = new ExploreClubsOutputData(inputData.getEmail(),
                    complement, false);
            getClubsPresenter.prepareSuccessView(outputData);
        }
    }
}
