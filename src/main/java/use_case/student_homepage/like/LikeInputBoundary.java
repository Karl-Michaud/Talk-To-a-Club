package use_case.student_homepage.like;

/**
 * Input boundary for the like usecase.
 */
public interface LikeInputBoundary {

    /**
     * Executes the like use case.
     * @param likeInputData the input data.
     */
    void execute(LikeInputData likeInputData);
}
