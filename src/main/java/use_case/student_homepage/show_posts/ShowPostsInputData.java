package use_case.student_homepage.show_posts;

/**
 * The input data for the show posts usecase.
 */
public class ShowPostsInputData {
    private final String currentUserEmail;

    public ShowPostsInputData(String userEmail) {
        this.currentUserEmail = userEmail;
    }

    String getUserEmail() {
        return currentUserEmail;
    }
}
