package use_case.signup.student_signup;

import entity.user.User;

/**
 * DAO for the Student Signup Use Case.
 */
public interface StudentSignupUserDataAccessInterface {

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
     * Saves the student user.
     * @param user the student user to save
     */
    void saveStudent(User user);

    /**
     * Creates a new unique identification number for a new user.
     * @return the identification number
     */
    Integer createId(User user);
}
