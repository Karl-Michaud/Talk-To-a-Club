package use_case.club_get_members;

import java.util.ArrayList;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.user.Club;
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
        final String clubEmail = inputData.getClubEmail();
        if (!getMembersDataAccessObject.existsByEmailClub(clubEmail)) {
            getMembersPresenter.prepareFailView(clubEmail + ": Account does not exist.");
        }
        else {
            // Get Club info
            final Club dbClub = getMembersDataAccessObject.getClub(clubEmail);
            final DataStore<String> membersNames = dbClub.getClubMembersNames();
            final DataStore<String> membersEmails = dbClub.getClubMembersEmails();

            // Create the array lists for the output data.
            final ArrayList<String> membersEmail = new ArrayList<>();
            final ArrayList<String> membersName = new ArrayList<>();

            // Populate the lists
            int index = 0;
            while (index < membersEmails.size()) {
                final String studentEmail = membersEmails.getByIndex(index);
                final String studentName = membersNames.getByIndex(index);
                // For a student at this index, assign membersEmail and membersName the values
                membersEmail.add(studentEmail);
                membersName.add(studentName);

                // Increment the index
                index++;
            }

            // Create the output data with the ArrayLists
            final ClubGetMembersOutputData outputData = new ClubGetMembersOutputData(inputData.getClubEmail(),
                    membersEmail, membersName, false);

            // Prepare the success view for the given output data
            getMembersPresenter.prepareSuccessView(outputData);
        }
    }
}
