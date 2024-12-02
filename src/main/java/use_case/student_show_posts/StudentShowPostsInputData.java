package use_case.student_show_posts;

/**
 * The input data for the show posts usecase.
 */
public class StudentShowPostsInputData {
    private final String currentUserEmail;

    public StudentShowPostsInputData(String userEmail) {
        this.currentUserEmail = userEmail;
    }

    String getUserEmail() {
        return currentUserEmail;
    }
}
