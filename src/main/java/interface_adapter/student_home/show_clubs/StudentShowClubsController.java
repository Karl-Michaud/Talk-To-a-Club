package interface_adapter.student_home.show_clubs;

import use_case.student_homepage.show_clubs.StudentShowClubsInputBoundary;
import use_case.student_homepage.show_clubs.StudentShowClubsInputData;

/**
 * Controller for the show clubs panel/ usecase.
 */
public class StudentShowClubsController {
    private final StudentShowClubsInputBoundary showClubsInteractor;

    public StudentShowClubsController(StudentShowClubsInputBoundary showClubsInteractor) {
        this.showClubsInteractor = showClubsInteractor;
    }

    /**
     * Executes the show clubs use case for signed in students.
     * @param currentUser the email of the current user.
     */
    public void execute(String currentUser) {
        final StudentShowClubsInputData inputData = new StudentShowClubsInputData(currentUser);
        showClubsInteractor.execute(inputData);
    }
}
