package use_case.student_get_clubs;

import entity.user.Student;

/**
 * Data access interface for the get clubs use case.
 */
public interface StudentGetClubsDataAccessInterface {

    /**
     * Checks if the email matches with a student in the database.
     * @param email of the potential student
     * @return true if the student exists with given email
     */
    boolean existsByEmailStudent(String email);

    /**
     * Returns the Student with given email.
     * Precondition: The student must exist.
     * @param email of the student.
     * @return the student with matching email.
     */
    Student getStudent(String email);
}
