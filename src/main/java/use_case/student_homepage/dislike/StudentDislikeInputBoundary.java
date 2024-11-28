package use_case.student_homepage.dislike;

/**
 * Input boundary for the dislike usecase.
 */
public interface StudentDislikeInputBoundary {

    /**
     * Executes the dislike use case.
     * @param studentDislikeInputData the input data.
     */
    void execute(StudentDislikeInputData studentDislikeInputData);
}
