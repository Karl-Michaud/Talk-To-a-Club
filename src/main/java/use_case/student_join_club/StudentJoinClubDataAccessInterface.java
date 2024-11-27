package use_case.student_join_club;

import entity.user.Student;

/**
 * Data Access Interface for Join Club use case.
 * Accesses only the Student entity to update its values.
 */
public interface StudentJoinClubDataAccessInterface {
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

    /**
     * Add the club in the student in the database.
     * @param student the student that should be added
     */
    void updateStudentClubsJoined(Student student);
}
