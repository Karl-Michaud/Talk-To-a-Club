package use_case.club_remove_member;

import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for the club remove member use case.
 */
public class ClubRemoveMemberInteractor implements ClubRemoveMemberInputBoundary {
    private final ClubRemoveMemberUserDataAccessInterface clubRemoveDataAccessObject;
    private final ClubRemoveMemberOutputBoundary clubRemovePresenter;

    public ClubRemoveMemberInteractor(ClubRemoveMemberUserDataAccessInterface clubRemoveDataAccessObject,
                                      ClubRemoveMemberOutputBoundary clubRemovePresenter) {
        this.clubRemoveDataAccessObject = clubRemoveDataAccessObject;
        this.clubRemovePresenter = clubRemovePresenter;
    }

    @Override
    public void execute(ClubRemoveMemberInputData inputData) {
        final String username = inputData.getStudentUsername();
        final String clubEmail = inputData.getClubEmail();

        // Since we are logged in, the club must exist by precondition, so we do not have to check if the club exists.
        final Club club = clubRemoveDataAccessObject.getClub(clubEmail);

        // Verify that the student even exists
        if (!clubRemoveDataAccessObject.existsByName(username)) {
            clubRemovePresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            // Get student with same username
            final Student student = clubRemoveDataAccessObject.getStudent(username);

            // Verify that student is in club
            if (!club.getClubMembers().containsKey(student.getUserID())) {
                clubRemovePresenter.prepareFailView(username + ": Account not in club.");
            }
            else {
                // Remove student from club
                clubRemoveDataAccessObject.removeStudent(student, club);
                club.removeClubMember(student);

                // Prepare success view
                final ClubRemoveMemberOutputData outputData = new ClubRemoveMemberOutputData(username, false);
                clubRemovePresenter.prepareSuccessView(outputData);
            }
        }
    }
}
