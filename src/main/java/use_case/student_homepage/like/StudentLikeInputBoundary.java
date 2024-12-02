package use_case.student_homepage.like;

/**
 * Input boundary for the like usecase.
 */
public interface StudentLikeInputBoundary {

    /**
     * Executes the like use case.
     * @param studentLikeInputData the input data.
     */
    void execute(StudentLikeInputData studentLikeInputData);
}
