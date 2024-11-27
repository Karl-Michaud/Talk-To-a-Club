package use_case.club_get_posts;

import entity.user.Club;

/**
 * Data access interface for the Club Get Posts Use Case.
 */
public interface ClubGetPostsDataAccessInterface {

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
}
