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
        final String studentEmail = inputData.getStudentEmail();
        final String clubEmail = inputData.getClubEmail();

        // Since we are logged in, the club must exist by precondition, so we do not have to check if the club exists.
        final Club club = clubRemoveDataAccessObject.getClub(clubEmail);

        // Verify that the student even exists
        if (!clubRemoveDataAccessObject.existsByEmailStudent(studentEmail)) {
            clubRemovePresenter.prepareFailView(studentEmail + ": Account does not exist.");
        }
        else {
            // Get student with same student email
            final Student student = clubRemoveDataAccessObject.getStudent(studentEmail);

            // Verify that student is in club
            if (!club.getClubMembers().contains(student)) {
                clubRemovePresenter.prepareFailView(studentEmail + ": Account not in club.");
            }
            else {
                // Remove student from club
                clubRemoveDataAccessObject.removeStudent(student, club);
                club.removeClubMember(student);

                // Prepare success view
                final ClubRemoveMemberOutputData outputData = new ClubRemoveMemberOutputData(student.getUsername(),
                        false);
                clubRemovePresenter.prepareSuccessView(outputData);
            }
        }
    }
}
