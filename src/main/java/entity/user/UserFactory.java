package entity.user;

/**
 * Factory for creating users. All types of user factories must implement this interface.
 */
public interface UserFactory {

    /**
     * Creates a new User basic user.
     * @param name the name of the new user
     * @param email the email of the new user
     * @param password the password of the new user
     * @return the new user
     */
    User create(String name, String email, String password);
}
