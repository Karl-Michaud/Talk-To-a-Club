package use_case.signup;

import entity.user.User;

/**
 * DAO for the Signup Use Case.
 */
public interface SignupUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the club user.
     * @param user the club user to save
     */
    void saveClub(User user);

    /**
     * Saves the student user.
     * @param user the student user to save
     */
    void saveStudent(User user);
}
