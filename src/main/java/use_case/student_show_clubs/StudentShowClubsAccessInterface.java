package use_case.student_show_clubs;

import java.util.ArrayList;

import entity.user.Club;
import entity.user.Student;

/**
 * Data access interface for the show clubs usecase.
 */
public interface StudentShowClubsAccessInterface {

    /**
     * Checks if the email matches with a club in the database.
     * @param email of the potential club.
     * @return true if the club exists with given email.
     */
    boolean existsByEmailStudent(String email);

    /**
     * Returns the followed clubs of the current user.
     * @param currentUser The current student that has logged in.
     * @return the student matching the email.
     */
    Student getStudent(String currentUser);

    /**
     * Gets the joined clubs for the given student.
     * @param student the student
     * @return an array lists of clubs
     */
    ArrayList<Club> getStudentJoinedClubs(Student student);

}
