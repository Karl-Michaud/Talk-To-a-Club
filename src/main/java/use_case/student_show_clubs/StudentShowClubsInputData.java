package use_case.student_show_clubs;

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
