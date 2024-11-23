package use_case.club_remove_member;

import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for the club remove member use case.
 */
public class ClubRemoveMemberInteractor implements ClubRemoveMemberInputBoundary {
    private final ClubRemoveMemberStudentDataAccessInterface clubRemoveStudentDataAccessObject;
    private final ClubRemoveMemberClubDataAccessInterface clubRemoveClubDataAccessObject;
    private final ClubRemoveMemberOutputBoundary clubRemovePresenter;

    public ClubRemoveMemberInteractor(ClubRemoveMemberStudentDataAccessInterface clubRemoveStudentDataAccessObject,
                                      ClubRemoveMemberClubDataAccessInterface clubRemoveClubDataAccessObject,
                                      ClubRemoveMemberOutputBoundary clubRemovePresenter) {
        this.clubRemoveStudentDataAccessObject = clubRemoveStudentDataAccessObject;
        this.clubRemoveClubDataAccessObject = clubRemoveClubDataAccessObject;
        this.clubRemovePresenter = clubRemovePresenter;
    }

    @Override
    public void execute(ClubRemoveMemberInputData inputData) {
        final String studentEmail = inputData.getStudentEmail();
        final String clubEmail = inputData.getClubEmail();

        // Since we are logged in, the club must exist by precondition, so we do not have to check if the club exists.
        final Club club = clubRemoveClubDataAccessObject.getClub(clubEmail);

        // Verify that the student even exists
        if (!clubRemoveStudentDataAccessObject.existsByEmailStudent(studentEmail)) {
            clubRemovePresenter.prepareFailView(studentEmail + ": Account does not exist.");
        }
        else {
            // Get student with same student email
            final Student student = clubRemoveStudentDataAccessObject.getStudent(studentEmail);

            // Verify that student is in club
            if (!club.getClubMembers().contains(student)) {
                clubRemovePresenter.prepareFailView(studentEmail + ": Account not in club.");
            }
            else {
                // Remove student from club
                student.leaveClub(club);
                clubRemoveStudentDataAccessObject.updateStudentClubsJoined(student);

                // Remove club from student
                club.removeClubMember(student);
                clubRemoveClubDataAccessObject.updateClubMembers(club);

                // Prepare success view
                final ClubRemoveMemberOutputData outputData = new ClubRemoveMemberOutputData(student.getUsername(),
                        false);
                clubRemovePresenter.prepareSuccessView(outputData);
            }
        }
    }
}
