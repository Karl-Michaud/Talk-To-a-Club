package use_case.student_homepage.show_posts;

import entity.user.Student;

/**
 * Interface for the show posts usecase.
 */
public interface ShowPostsAccessInterface {

    /**
     * Returns the posts of the associated clubs the current student follows.
     * @param currentUser The current student that is logged in.
     */
    Student getStudent(String currentUser);
}
