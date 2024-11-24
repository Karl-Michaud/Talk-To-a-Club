package use_case.student_get_clubs;

/**
 * Output boundary for the get clubs use case.
 */
public interface StudentGetClubsOutputBoundary {
    /**
     * Prepares the success view for the get members Use Case for students.
     * @param data the output data
     */
    void prepareSuccessView(StudentGetClubsOutputData data);

    /**
     * Prepares the failure view for the get members use case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
