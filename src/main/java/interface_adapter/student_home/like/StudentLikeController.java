package interface_adapter.student_home.like;

import java.util.Map;

import use_case.student_homepage.like.StudentLikeInputBoundary;
import use_case.student_homepage.like.StudentLikeInputData;

/**
 * Controller for the like usecase on the student homepage.
 */
public class StudentLikeController {
    private final StudentLikeInputBoundary studentLikeInputBoundary;

    public StudentLikeController(StudentLikeInputBoundary studentLikeInputBoundary) {
        this.studentLikeInputBoundary = studentLikeInputBoundary;
    }

    /**
     * Adds the like to the post.
     * @param studentEmail the email of the current logged in student.
     * @param postData the post which has been liked.
     */
    public void changeLike(String studentEmail, Map<String, Object> postData) {
        final StudentLikeInputData studentLikeInputData = new StudentLikeInputData(studentEmail, postData);
        studentLikeInputBoundary.execute(studentLikeInputData);
    }
}
