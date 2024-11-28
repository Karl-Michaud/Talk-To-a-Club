package use_case.student_homepage.show_posts;

import entity.user.Student;

/**
 * Interface for the show posts usecase.
 */
public interface StudentShowPostsAccessInterface {

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
}
