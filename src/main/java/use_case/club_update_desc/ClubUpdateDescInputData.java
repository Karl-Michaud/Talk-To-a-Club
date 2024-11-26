package use_case.club_update_desc;

/**
 * The input data for the Club Update Description Use Case.
 */
public class ClubUpdateDescInputData {

    private final String clubEmail;
    private final String newDescription;

    public ClubUpdateDescInputData(String clubEmail, String newDescription) {
        this.clubEmail = clubEmail;
        this.newDescription = newDescription;
    }

    public String getClubEmail() {
        return this.clubEmail;
    }

    public String getNewDescription() {
        return this.newDescription;
    }
}
