package use_case.explore_clubs;

import entity.user.Student;

/**
 * Data access interface for the explore clubs use case.
 * Handles only the Student entity data.
 */
public interface StudentExploreClubsDataAccessInterface {

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
