package use_case.student_join_club;

import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for join club use case.
 */
public class StudentJoinClubInteractor implements StudentJoinClubInputBoundary {
    private final StudentJoinClubDataAccessInterface studentJoinClubDataAccessInterface;
    private final ClubStudentJoinClubDataAccessInterface clubStudentJoinClubDataAccessInterface;
    private final StudentJoinClubOutputBoundary joinClubPresenter;

    public StudentJoinClubInteractor(StudentJoinClubDataAccessInterface studentJoinClubDataAccessInterface,
                                      ClubStudentJoinClubDataAccessInterface clubStudentJoinClubDataAccessInterface,
                                      StudentJoinClubOutputBoundary joinClubPresenter) {
        this.studentJoinClubDataAccessInterface = studentJoinClubDataAccessInterface;
        this.clubStudentJoinClubDataAccessInterface = clubStudentJoinClubDataAccessInterface;
        this.joinClubPresenter = joinClubPresenter;
    }

    @Override
    public void execute(StudentJoinClubInputData inputData) {
        final String studentEmail = inputData.getStudentEmail();
        final String clubEmail = inputData.getClubEmail();

        // Since we are logged in, the student must exist by precondition,
        // so we do not have to check if the student exists.
        final Club club = clubStudentJoinClubDataAccessInterface.getClub(clubEmail);

        // Verify that the club even exists
        if (!clubStudentJoinClubDataAccessInterface.existsByEmailClub(clubEmail)) {
            joinClubPresenter.prepareFailView(studentEmail + ": Club does not exist.");
        }
        else {
            // Get student with same student email
            final Student student = studentJoinClubDataAccessInterface.getStudent(studentEmail);

            // Verify that student is not in club
            if (club.getClubMembersEmails().contains(student.getEmail())) {
                joinClubPresenter.prepareFailView(studentEmail + ": Account in club already.");
            }
            else {
                // Add club into student following.
                student.joinClub(club);
                studentJoinClubDataAccessInterface.updateStudentClubsJoined(student);

                // Add student into club list.
                club.addClubMember(student);
                clubStudentJoinClubDataAccessInterface.updateClubMembers(club);

                // Prepare success view
                final StudentJoinClubOutputData outputData = new StudentJoinClubOutputData(student.getUsername(),
                        clubEmail, false);
                joinClubPresenter.prepareSuccessView(outputData);
            }
        }
    }
}
