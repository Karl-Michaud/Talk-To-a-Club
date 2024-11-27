package interface_adapter.student_home.like;

import java.util.Map;

import use_case.student_homepage.like.LikeInputBoundary;
import use_case.student_homepage.like.LikeInputData;

/**
 * Controller for the like usecase on the student homepage.
 */
public class StudentLikeController {
    private final LikeInputBoundary likeInputBoundary;

    public StudentLikeController(LikeInputBoundary likeInputBoundary) {
        this.likeInputBoundary = likeInputBoundary;
    }

    /**
     * Adds the like to the post.
     * @param studentEmail the email of the current logged in student.
     * @param postData the post which has been liked.
     */
    public void changeLike(String studentEmail, Map<String, Object> postData) {
        final LikeInputData likeInputData = new LikeInputData(studentEmail, postData);
        likeInputBoundary.execute(likeInputData);
    }
}
