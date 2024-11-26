package use_case.club_update_desc;

/**
 * The output boundary for the Club Update Description use case.
 */
public interface ClubUpdateDescOutputBoundary {

    /**
     * Prepares the message for the Club Home View.
     * @param message the message passed to the view
     */
    void prepareMessage(String message);
}
