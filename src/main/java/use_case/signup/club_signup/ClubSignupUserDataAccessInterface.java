package use_case.signup.club_signup;

import entity.user.User;

/**
 * DAO for the Club Signup Use Case.
 */
public interface ClubSignupUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Checks if the given email exists.
     * @param email the email to look for
     * @return true if a user with the given email exists; false otherwise
     */
    boolean existsByEmail(String email);

    /**
     * Saves the club user.
     * @param user the club user to save
     */
    void saveClub(User user);

    /**
     * Creates a new unique identification number for a new user.
     * @return the identification number
     */
    Integer createId(User user);
}
