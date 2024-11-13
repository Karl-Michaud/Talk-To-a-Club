package use_case.club_create_post;

/**
 * Input boundary for creating posts use case.
 */
public interface ClubCreatePostInputBoundary {

    /**
     * Executes the club post use case.
     * @param clubCreatePostInputData the input data
     */
    void execute(ClubCreatePostInputData clubCreatePostInputData);

}
