package use_case.student_homepage.show_posts;

/**
 * The input data for the show posts usecase.
 */
public class ShowPostsInputData {
    private String currentUser;

    public ShowPostsInputData(String user) {
        this.currentUser = user;
    }

    String getUser() {
        return currentUser;
    }
}
