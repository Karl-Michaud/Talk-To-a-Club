package use_case.explore_clubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entity.data_structure.DataStore;
import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for the get clubs use case.
 */
public class ExploreClubsInteractor implements ExploreClubsInputBoundary {
    private final StudentExploreClubsDataAccessInterface studentExploreClubsDataAccessInterface;
    private final ClubExploreClubsDataAccessInterface clubExploreClubsDataAccessInterface;
    private final ExploreClubsOutputBoundary exploreClubsPresenter;

    public ExploreClubsInteractor(StudentExploreClubsDataAccessInterface studentExploreClubsDataAccessInterface,
                                  ExploreClubsOutputBoundary getMembersPresenter,
                                  ClubExploreClubsDataAccessInterface clubExploreClubsDataAccessInterface) {
        this.studentExploreClubsDataAccessInterface = studentExploreClubsDataAccessInterface;
        this.clubExploreClubsDataAccessInterface = clubExploreClubsDataAccessInterface;
        this.exploreClubsPresenter = getMembersPresenter;
    }

    /**
     * Executes the get clubs use case.
     * @param inputData the input data.
     */
    @Override
    public void execute(ExploreClubsInputData inputData) {
        final String email = inputData.getEmail();
        if (!studentExploreClubsDataAccessInterface.existsByEmailStudent(email)) {
            exploreClubsPresenter.prepareFailView(email + ": Account does not exist.");
        }
        else {
            // gets the joined clubs by retrieving user from database and getting joined clubs.
            final Student student = studentExploreClubsDataAccessInterface.getStudent(email);
            final DataStore<Club> joinedClubs = student.getJoinedClubs();

            // gets all the clubs from the database.
            final DataStore<Club> allClubs = clubExploreClubsDataAccessInterface.getAllClubs();

            // all values of all clubs minus the joined ones
            final DataStore<Club> complement = allClubs.complement(joinedClubs);

            final ArrayList<Map<String, String>> outputMap = new ArrayList<>();

            // TODO add these to constants file

            for (Club club : complement.getAll()) {
                final Map<String, String> map = new HashMap<>();
                map.put("username", club.getUsername());
                map.put("email", club.getEmail());
                map.put("description", club.getClubDescription());
                map.put("numMembers", club.getClubMembers().size().toString());
                outputMap.add(map);
            }
            final ArrayList<String> joinedClubsEmails = new ArrayList<>();
            for (Club club : joinedClubs.getAll()) {
                joinedClubsEmails.add(club.getEmail());
            }

            final ExploreClubsOutputData outputData = new ExploreClubsOutputData(
                    outputMap, false, student.getEmail(), joinedClubsEmails);
            System.out.println("Code reaching to the end of the interactor");
            exploreClubsPresenter.prepareSuccessView(outputData);
        }
    }

    @Override
    public void switchToClubView(Map<String, String> club) {
        exploreClubsPresenter.switchToClubView(club);
    }

    @Override
    public void switchToHomeView() {
        exploreClubsPresenter.switchToHomeView();
    }
}
