package use_case.club_get_members;

import java.util.ArrayList;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
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
            final DataStore<Student> members = getMembersDataAccessObject.getClub(clubEmail).getClubMembers();

            // Create the array lists for the output data.
            final ArrayList<String> membersEmail = new ArrayList<>();
            final ArrayList<String> membersName = new ArrayList<>();

            int index = 0;
            while (index < members.size()) {
                final Student student = members.getByIndex(index);
                // For a student at this index, assign membersEmail and membersName the values
                membersEmail.add(student.getEmail());
                membersName.add(student.getUsername());

                // Increment the index
                index++;
            }

            // Create the output data with the ArrayLists
            final ClubGetMembersOutputData outputData = new ClubGetMembersOutputData(inputData.getClubEmail(), membersEmail,
                    membersName, false);

            // Prepare the success view for the given output data
            getMembersPresenter.prepareSuccessView(outputData);
        }
    }
}
