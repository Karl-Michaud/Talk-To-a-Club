package use_case.club_get_members;

import java.util.Map;

import entity.user.Club;
import entity.user.Student;

/**
 * Data access interface for the get members use case.
 */
public interface ClubGetMembersUserDataAccessInterface {

    /**
     * Checks if the email matches with a club in the database.
     * @param email of the potential club
     * @return true if the club exists with given email
     */
    boolean existsByEmail(String email);

    /**
     * Returns the Clubs with given email.
     * @param email of the club
     * @return the club with matching email
     */
    Club getClub(String email);

    /**
     * Get members of a given club.
     * @param club the Club
     * @return the members of the given club.
     */
    Map<Integer, Student> getMembers(Club club);
}
