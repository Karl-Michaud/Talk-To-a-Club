package use_case.club_create_post;

/**
 * Output boundary for post creation use case.
 */
public interface ClubCreatePostOutputBoundary {
    /**
     * Prepares the success view for the post creation Use Case for students.
     * @param data the output data
     */
    void prepareSuccessView(ClubCreatePostOutputData data);

    /**
     * Prepares the failure view for the post creation Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

}
