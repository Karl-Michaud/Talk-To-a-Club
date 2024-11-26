package interface_adapter.student_home.like;

import entity.post.Post;
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
     * @param clubName the name of the club whose post has been liked.
     * @param post the post which has been liked.
     */
    public void changeLike(String studentEmail, String clubName, Post post) {
        final LikeInputData likeInputData = new LikeInputData(studentEmail, clubName, post);
        likeInputBoundary.execute(likeInputData);
    }
}
