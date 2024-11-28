package interface_adapter.student_home.dislike;

import java.util.Map;

import use_case.student_homepage.dislike.StudentDislikeInputBoundary;
import use_case.student_homepage.dislike.StudentDislikeInputData;

/**
 * Controller for the dislike usecase on the student home page.
 */
public class StudentDislikeController {
    private final StudentDislikeInputBoundary studentDislikeInputBoundary;

    public StudentDislikeController(StudentDislikeInputBoundary studentDislikeInputBoundary) {
        this.studentDislikeInputBoundary = studentDislikeInputBoundary;
    }

    /**
     * Adds the dislike to the posts.
     * @param studentEmail the email of the current logged in student.
     * @param postData the post which has been liked.
     */
    public void changeDislike(String studentEmail, Map<String, Object> postData) {
        final StudentDislikeInputData studentDislikeInputData = new StudentDislikeInputData(studentEmail, postData);
        studentDislikeInputBoundary.execute(studentDislikeInputData);
    }
}
