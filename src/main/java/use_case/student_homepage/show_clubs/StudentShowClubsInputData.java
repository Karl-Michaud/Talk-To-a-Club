package use_case.student_homepage.show_clubs;

/**
 * The input data for the show clubs usecase.
 */
public class StudentShowClubsInputData {
    private final String currentUserEmail;

    public StudentShowClubsInputData(String currentUser) {
        this.currentUserEmail = currentUser;
    }

    String getUserEmail() {
        return currentUserEmail;
    }
}
