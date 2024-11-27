package use_case.student_homepage.show_clubs;

/**
 * The input data for the show clubs usecase.
 */
public class ShowClubsInputData {
    private String currentUser;

    public ShowClubsInputData(String currentUser) {
        this.currentUser = currentUser;
    }

    String getUser() {
        return currentUser;
    }
}
