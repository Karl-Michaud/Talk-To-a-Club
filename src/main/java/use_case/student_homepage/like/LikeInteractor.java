package use_case.student_homepage.like;

import java.util.Map;

/**
 * Interactor for the like usecase.
 */
public class LikeInteractor implements LikeInputBoundary {
    private final LikeAccessInterface likeAccessInterface;

    public LikeInteractor(LikeAccessInterface likeAccessInterface) {
        this.likeAccessInterface = likeAccessInterface;
    }

    @Override
    public void execute(LikeInputData likeInputData) {
        final Map<String, Object> post = likeInputData.getPost();
        final String studentEmail = likeInputData.getStudentEmail();
        final Boolean isLiked = likeAccessInterface.getPostLiked(studentEmail, post);

        if (isLiked) {
            likeAccessInterface.likePost(post, studentEmail);
        }
        else {
            likeAccessInterface.unLikePost(post, studentEmail);
        }
    }
}
