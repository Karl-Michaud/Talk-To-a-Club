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
}
