package interface_adapter.club_update_desc;

import use_case.club_update_desc.ClubUpdateDescInputBoundary;
import use_case.club_update_desc.ClubUpdateDescInputData;

/**
 * Controller for the Club Update Description Use Case.
 */
public class ClubUpdateDescController {

    private final ClubUpdateDescInputBoundary descUpdateInteractor;

    public ClubUpdateDescController(ClubUpdateDescInputBoundary descUpdateInteractor) {
        this.descUpdateInteractor = descUpdateInteractor;

    }

    /**
     * Executes the Club Home Update Description Use Case.
     * @param clubEmail the email of the club to update
     * @param newDescription the new description
     */
    public void execute(String clubEmail, String newDescription) {
        final ClubUpdateDescInputData descUpdateInputData = new ClubUpdateDescInputData(clubEmail, newDescription);

        descUpdateInteractor.execute(descUpdateInputData);
    }
}
