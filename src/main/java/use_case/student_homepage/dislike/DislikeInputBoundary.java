package use_case.student_homepage.dislike;

/**
 * Input boundary for the dislike usecase.
 */
public interface DislikeInputBoundary {

    /**
     * Executes the dislike use case.
     * @param dislikeInputData the input data.
     */
    void execute(DislikeInputData dislikeInputData);
}
