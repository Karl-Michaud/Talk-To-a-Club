package use_case.signup.student_signup;

import entity.user.Student;

/**
 * DAO for the Student Signup Use Case.
 */
public interface StudentSignupDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByNameStudent(String username);

    /**
     * Checks if the given email exists.
     * @param email the email to look for
     * @return true if a user with the given email exists; false otherwise
     */
    boolean existsByEmailStudent(String email);

    /**
     * Saves the student user.
     * @param user the student user to save
     */
    void saveStudent(Student user);

}
