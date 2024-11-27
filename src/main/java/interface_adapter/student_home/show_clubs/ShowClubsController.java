package interface_adapter.student_home.show_clubs;

import use_case.student_homepage.show_clubs.ShowClubsInputBoundary;
import use_case.student_homepage.show_clubs.ShowClubsInputData;

/**
 * Controller for the show clubs panel/ usecase.
 */
public class ShowClubsController {
    private final ShowClubsInputBoundary showClubsInteractor;

    public ShowClubsController(ShowClubsInputBoundary showClubsInteractor) {
        this.showClubsInteractor = showClubsInteractor;
    }

    /**
     * Populates the posts panel on the homepage view.
     * @param currentUser the email of the current user.
     */
    public void execute(String currentUser) {
        final ShowClubsInputData inputData = new ShowClubsInputData(currentUser);
        showClubsInteractor.execute(inputData);
    }
}
