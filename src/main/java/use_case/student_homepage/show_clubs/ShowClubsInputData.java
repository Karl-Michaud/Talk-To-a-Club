package use_case.student_homepage.show_clubs;

/**
 * The input data for the show clubs usecase.
 */
public class ShowClubsInputData {
    private final String currentUserEmail;

    public ShowClubsInputData(String currentUser) {
        this.currentUserEmail = currentUser;
    }

    String getUserEmail() {
        return currentUserEmail;
    }
}
