package use_case.club_remove_member;

import entity.user.Club;
import entity.user.Student;

/**
 * Data access interface for club remove member use case.
 */
public interface ClubRemoveMemberClubDataAccessInterface {

    /**
     * Returns club with given email.
     * @param clubEmail the club's email
     * @return the club with given email.
     */
    Club getClub(String clubEmail);

    /**
     * Remove the student from the club in the database.
     * @param student the student that should be removed
     * @param club the club from which the student needs to be removed from
     */
    void removeStudentFromClub(Student student, Club club);
}
