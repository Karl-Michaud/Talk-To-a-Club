package use_case.login.student_login;

import entity.user.Student;

/**
 * Data access interface for the student use case.
 */
public interface StudentLoginDataAccessInterface {

    /**
    * Checks if the given student email exists in the database.
    * @param email the student email to look for
    * @return true if a Student with the given student email exists in the database.
     */
    boolean existsByEmailStudent(String email);

    /**
     * Returns the student with the given email.
     * Precondition: The student must exist.
     * @param studentEmail of the student we are looking for
     * @return the student with the given studentEmail
     */
    Student getStudent(String studentEmail);
}
