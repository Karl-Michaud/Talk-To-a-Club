package use_case.student_join_club;

import entity.user.Club;

/**
 * Data access interface for the Student join club use case.
 * Handles the data for the club entity.
 */
public interface ClubStudentJoinClubDataAccessInterface {
    /**
     * Returns club with given email.
     * @param clubEmail the club's email
     * @return the club with given email.
     */
    Club getClub(String clubEmail);

    /**
     * Checks if the given club email exists in the database.
     * @param email the club email to look for
     * @return true if a Club with the given club email exists in the database.
     */
    boolean existsByEmailClub(String email);

    /**
     * Adds the student in the club in the database.
     * @param club the club from which the student needs to be removed from
     */
    void updateClubMembers(Club club);
}
