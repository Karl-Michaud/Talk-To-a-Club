package use_case.student_leave_club;

import entity.user.Student;

/**
 * Data access interface for leave club use case.
 * Handles only the Student entity.
 */
public interface StudentLeaveClubDataAccessInterface {
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
     * Updates the clubs that the student has joined.
     * @param student the student that should be updated
     */
    void updateStudentClubsJoined(Student student);
}
