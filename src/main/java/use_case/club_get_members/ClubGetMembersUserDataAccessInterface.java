package use_case.club_get_members;

import entity.user.Club;

/**
 * Data access interface for the get members use case.
 */
public interface ClubGetMembersUserDataAccessInterface {

    /**
     * Checks if the email matches with a club in the database.
     * @param email of the potential club
     * @return true if the club exists with given email
     */
    boolean existsByEmailClub(String email);

    /**
     * Returns the Clubs with given email.
     * Precondition: The club must exist.
     * @param email of the club
     * @return the club with matching email
     */
    Club getClub(String email);
}
