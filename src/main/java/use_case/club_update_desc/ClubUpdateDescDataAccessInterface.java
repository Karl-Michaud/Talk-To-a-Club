package use_case.club_update_desc;

import entity.user.Club;

/**
 * Data access interface for the Club Update Description Use Case.
 */
public interface ClubUpdateDescDataAccessInterface {

    /**
     * Returns club with given email.
     * @param clubEmail the club's email
     * @return the club with given email.
     */
    Club getClub(String clubEmail);

    /**
     * Checks if the given email exists in the database.
     * @param email the email to look for
     * @return true if a Club with the given email exists in the database.
     */
    boolean existsByEmailClub(String email);

    /**
     * Updates the given club's description in the database.
     * @param club the club object with the updated description
     */
    void updateClubDescription(Club club);
}
