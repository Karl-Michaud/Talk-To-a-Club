package use_case.student_explore_clubs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
            final DataStore<String> joinedClubsEmails = student.getJoinedClubsEmails();

            // gets all the clubs from the database.
            final DataStore<Club> allClubs = clubExploreClubsDataAccessInterface.getAllClubs();
            System.out.println(allClubs);

            // all values of all clubs minus the joined ones
            final DataStore<Club> complement = new DataStoreArrays<>();
            int index = 0;
            while (index < allClubs.size()) {
                final Club club = allClubs.getByIndex(index);
                final String clubEmail = club.getEmail();
                // Check if the student has joined this club. If it did not, then add to complement
                if (!joinedClubsEmails.contains(clubEmail)) {
                    complement.add(club);
                }
                index++;
            }

            final ArrayList<Map<String, String>> outputMap = new ArrayList<>();

            // TODO add these to constants file

            for (Club club : complement.getAll()) {
                final Map<String, String> map = new HashMap<>();
                map.put("username", club.getUsername());
                map.put("email", club.getEmail());
                map.put("description", club.getClubDescription());
                map.put("numMembers", club.getClubMembersNames().size().toString());
                outputMap.add(map);
            }
            final ArrayList<String> joinedEmails = new ArrayList<>();
            int i = 0;
            while (i < joinedClubsEmails.size()) {
                joinedEmails.add(joinedClubsEmails.getByIndex(i));
                i++;
            }

            final ExploreClubsOutputData outputData = new ExploreClubsOutputData(
                    outputMap, false, student.getEmail(), joinedEmails);
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
