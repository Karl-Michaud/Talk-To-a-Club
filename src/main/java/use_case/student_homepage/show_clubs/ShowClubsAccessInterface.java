package use_case.student_homepage.show_clubs;

import entity.user.Student;

/**
 * Data access interface for the show clubs usecase.
 */
public interface ShowClubsAccessInterface {
    /**
     * Returns the followed clubs of the current user.
     * @param currentUser The current student that has logged in.
     */
    Student getStudent(String currentUser);
}
