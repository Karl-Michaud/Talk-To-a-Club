package use_case.student_homepage.like;

/**
 * Output boundary for the student like usecase.
 */
public interface LikeOutputBoundary {
    /**
     * Updates the view to include the like on the student home page.
     * @param data the output data.
     */
    void prepareSuccessView(LikeOutputData data);

    /**
     * Prepares an error message in case liking the post fails.
     * @param errorMessage the error message explaining the failure.
     */
    void prepareErrorView(String errorMessage);
}
