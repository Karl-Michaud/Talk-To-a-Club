package use_case.student_homepage.dislike;

import java.util.Map;

/**
 * Interactor for the dislike usecase.
 */
public class DislikeInteractor implements DislikeInputBoundary {
    private final DislikeAccessInterface dislikeAccessInterface;

    public DislikeInteractor(DislikeAccessInterface dislikeAccessInterface) {
        this.dislikeAccessInterface = dislikeAccessInterface;
    }

    @Override
    public void execute(DislikeInputData dislikeInputData) {
        final Map<String, Object> post = dislikeInputData.getPost();
        final String studentEmail = dislikeInputData.getStudentEmail();
        final Boolean isLiked = dislikeAccessInterface.getPostDisliked(studentEmail, post);

        if (isLiked) {
            dislikeAccessInterface.dislikePost(post, studentEmail);
        }
        else {
            dislikeAccessInterface.unDislikePost(post, studentEmail);
        }
    }
}
