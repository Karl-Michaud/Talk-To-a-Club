package use_case.student_show_posts;

import java.util.ArrayList;

import entity.user.Club;
import entity.user.Student;

/**
 * Interface for the show posts usecase.
 */
public interface StudentShowPostsStudentAccessInterface {

    /**
     * Checks if the email matches with a club in the database.
     * @param email of the potential club.
     * @return true if the club exists with given email.
     */
    boolean existsByEmailStudent(String email);

    /**
     * Returns the posts of the associated clubs the current student follows.
     * @param currentUser The current student that is logged in.
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
