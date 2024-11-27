package interface_adapter.student_home.dislike;

import java.util.Map;

import use_case.student_homepage.dislike.DislikeInputBoundary;
import use_case.student_homepage.dislike.DislikeInputData;

/**
 * Controller for the dislike usecase on the student home page.
 */
public class StudentDislikeController {
    private final DislikeInputBoundary dislikeInputBoundary;

    public StudentDislikeController(DislikeInputBoundary dislikeInputBoundary) {
        this.dislikeInputBoundary = dislikeInputBoundary;
    }

    /**
     * Adds the dislike to the posts.
     * @param studentEmail the email of the current logged in student.
     * @param postData the post which has been liked.
     */
    public void changeDislike(String studentEmail, Map<String, Object> postData) {
        final DislikeInputData dislikeInputData = new DislikeInputData(studentEmail, postData);
        dislikeInputBoundary.execute(dislikeInputData);
    }
}
