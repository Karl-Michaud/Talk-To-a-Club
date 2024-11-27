package use_case.student_leave_club;

import entity.user.Club;

/**
 * Data access interface for leave club use case.
 * Handles only the Club entity data accessing.
 */
public interface ClubStudentLeaveClubDataAccessInterface {
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
     * Updates the clubs member list.
     * @param club the club from which the student needs to be removed from.
     */
    void updateClubMembers(Club club);
}
