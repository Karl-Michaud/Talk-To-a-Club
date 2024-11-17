package use_case.club_remove_member;

import entity.user.Club;
import entity.user.Student;

/**
 * Data access interface for club remove member use case.
 */
public interface ClubRemoveMemberUserDataAccessInterface {

    /**
     * Returns club with given email.
     * @param clubEmail the club's email
     * @return the club with given email.
     */
    Club getClub(String clubEmail);

    /**
     * Checks if the student with given username exists.
     * @param studentUsername the username of the student we want to check
     * @return a boolean. True if the student exists, false else.
     */
    boolean existsByName(String studentUsername);

    /**
     * Return student with given username. Make sure to check that the student exists.
     * @param studentUsername student's username
     * @return the student with given username
     */
    Student getStudent(String studentUsername);

    /**
     * Remove the student from the club in the database.
     * @param student the student that should be removed
     * @param club the club from which the student needs to be removed from
     */
    void removeStudent(Student student, Club club);
}
