package use_case.student_leave_club;

import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for leave club use case.
 */
public class StudentLeaveClubInteractor implements StudentLeaveClubInputBoundary {
    private final StudentLeaveClubDataAccessInterface studentLeaveClubDataAccessInterface;
    private final ClubStudentLeaveClubDataAccessInterface clubStudentLeaveClubDataAccessInterface;
    private final StudentLeaveClubOutputBoundary studentLeavePresenter;

    public StudentLeaveClubInteractor(StudentLeaveClubDataAccessInterface studentLeaveClubDataAccessInterface,
                                      ClubStudentLeaveClubDataAccessInterface clubStudentLeaveClubDataAccessInterface,
                                      StudentLeaveClubOutputBoundary studentLeavePresenter) {
        this.studentLeaveClubDataAccessInterface = studentLeaveClubDataAccessInterface;
        this.clubStudentLeaveClubDataAccessInterface = clubStudentLeaveClubDataAccessInterface;
        this.studentLeavePresenter = studentLeavePresenter;
    }

    @Override
    public void execute(StudentLeaveClubInputData inputData) {
        final String studentEmail = inputData.getStudentEmail();
        final String clubEmail = inputData.getClubEmail();

        // Since we are logged in, the student must exist by precondition,
        // so we do not have to check if the student exists.
        final Club club = clubStudentLeaveClubDataAccessInterface.getClub(clubEmail);

        // Verify that the club even exists
        if (!clubStudentLeaveClubDataAccessInterface.existsByEmailClub(clubEmail)) {
            studentLeavePresenter.prepareFailView(studentEmail + ": Club does not exist.");
        }
        else {
            // Get student with same student email
            final Student student = studentLeaveClubDataAccessInterface.getStudent(studentEmail);

            // Verify that student is in club
            if (!club.getClubMembers().contains(student)) {
                studentLeavePresenter.prepareFailView(studentEmail + ": Account not in club.");
            }
            else {
                // Remove student from club and save change
                student.leaveClub(club);
                studentLeaveClubDataAccessInterface.updateStudentClubsJoined(student);

                // Remove club from student and save change
                club.removeClubMember(student);
                clubStudentLeaveClubDataAccessInterface.updateClubMembers(club);

                // Prepare success view
                final StudentLeaveClubOutputData outputData = new StudentLeaveClubOutputData(student.getUsername(),
                        false);
                studentLeavePresenter.prepareSuccessView(outputData);
            }
        }
    }

}
