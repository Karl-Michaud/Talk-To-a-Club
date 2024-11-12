package use_case.student_login;

import entity.user.Student;

/**
 * Data access interface for the student use case.
 */
public interface StudentLoginDataAccessInterface {

    /**
    * Checks if the given username exists in the database.
    * @param username the username to look for
    * @return true if a Student with the given username exists in the database.
     */
    boolean existsByName(String username);

    /**
     * Returns the student with the given username.
     * @param username of the student we are looking for
     * @return the student with the given username
     */
    Student getStudent(String username);
}
