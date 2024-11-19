package use_case.club_get_members;

import java.util.ArrayList;

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
            final ArrayList<Student> members = getMembersDataAccessObject.getClub(email).getClubMembers();

            final ClubGetMembersOutputData outputData = new ClubGetMembersOutputData(inputData.getEmail(), members,
                    false);
            getMembersPresenter.prepareSuccessView(outputData);
        }
    }
}
