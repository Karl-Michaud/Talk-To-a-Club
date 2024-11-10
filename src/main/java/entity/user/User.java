package entity.user;

/**
 * User interface. All users must implement the following interface.
 * As of now, we only plan on having Student users and Club users.
 */
public interface User {
    /**
     * Returns the user's name.
     * @return a String username
     */
    String getUsername();

    /**
     * Returns the user's email address.
     * @return a String email
     */
    String getEmail();

    /**
     * Returns the user's password.
     * @return a String password
     */
    String getPassword();

    /**
     * Returns the user's identification.
     * @return an int type ID
     */
    int getUserID();

    /**
     * Sets the user's identification.
     * @param id identification number for the user.
     */
    void setUserID(int id);
}
