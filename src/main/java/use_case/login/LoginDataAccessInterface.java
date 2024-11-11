package use_case.login;

import entity.user.User;

public interface LoginDataAccessInterface {

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
     * Saves the student user.
     * @param user the student user to save
     */
    void saveStudent(User user);

    /**
     * Returns the user with the given email.
     * @param email the username to look up
     * @return the user with the given email
     */
    User get(String email);

    /**
     * Sets the current username.
     * @param name the name of the user.
     */
    void setCurrentUser(String name);

    /**
     * Gets the current user.
     * @return String of the user.
     */
    String getCurrentUser();
}
