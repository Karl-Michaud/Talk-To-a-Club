package use_case.student_get_clubs;

/**
 * Input boundary interface for the get clubs use case.
 */
public interface StudentGetClubsInputBoundary {

    /**
     * Executes the get clubs use case.
     * @param studentGetClubsInputData the input data.
     */
    void execute(StudentGetClubsInputData studentGetClubsInputData);
}
