package use_case.student_homepage.like;

import entity.user.Student;

/**
 * Interface for the like usecase.
 */
public interface LikeAccessInterface {

    /**
     * Returns the posts of the associated clubs the current student follows.
     * @param currentUser The current student that is logged in.
     */
    Student getStudent(String currentUser);

}
