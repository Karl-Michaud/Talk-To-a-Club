package use_case.club_remove_member;

import entity.user.Student;

/**
 * Data access interface for club remove member use case.
 */
public interface ClubRemoveMemberStudentDataAccessInterface {

    /**
     * Checks if the student with given username exists.
     * @param email the username of the student we want to check
     * @return a boolean. True if the student exists, false else.
     */
    boolean existsByEmailStudent(String email);

    /**
     * Return student with given username. Make sure to check that the student exists.
     * Precondition: The student must exist.
     * @param studentEmail student's username
     * @return the student with given username
     */
    Student getStudent(String studentEmail);

    /**
     * Remove the club from the student in the database.
     * @param student the student that should be removed
     */
    void updateStudentClubsJoined(Student student);
}
