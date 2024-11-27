package use_case.club_update_desc;

/**
 * The output data for the Club Update Description use case.
 */
public class ClubUpdateDescOutputData {

    // Currently unused but is here for future expansion

    private final String message;
    private final String newDesc;

    public ClubUpdateDescOutputData(String message, String newDesc) {
        this.message = message;
        this.newDesc = newDesc;
    }

    public String getMessage() {
        return this.message;
    }

    public String getNewDesc() {
        return this.newDesc;
    }
}
