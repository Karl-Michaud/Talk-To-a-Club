package use_case.student_join_club;

/**
 * Input boundary for the Join club use case.
 */
public interface StudentJoinClubInputBoundary {
    /**
     * Executes the join club use case.
     * @param studentJoinClubInputData the input data.
     */
    void execute(StudentJoinClubInputData studentJoinClubInputData);
}
