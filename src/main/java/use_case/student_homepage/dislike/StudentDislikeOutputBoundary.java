package use_case.student_homepage.dislike;

/**
 * Output boundary for the student dislike usecase.
 */
public interface StudentDislikeOutputBoundary {
    /**
     * Updates the view to include the dislike on the student home page.
     * @param data the output data.
     */
    void prepareSuccessView(StudentDislikeOutputData data);

    /**
     * Prepares an error message in case disliking the post fails.
     * @param errorMessage the error message explaining the failure.
     */
    void prepareErrorView(String errorMessage);
}
