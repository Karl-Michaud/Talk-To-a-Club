package use_case.club_update_desc;

import entity.user.Club;

/**
 * The Club Update Description Interactor.
 */
public class ClubUpdateDescInteractor implements ClubUpdateDescInputBoundary {
    private final ClubUpdateDescDataAccessInterface updateDescDataAccessObject;

    public ClubUpdateDescInteractor(ClubUpdateDescDataAccessInterface updateDescDataAccessObject) {
        this.updateDescDataAccessObject = updateDescDataAccessObject;
    }

    @Override
    public void execute(ClubUpdateDescInputData descUpdateInputData) {
        // Gets the current club entity and changes its description to the new one
        Club currentClub = updateDescDataAccessObject.getClub(descUpdateInputData.getClubEmail());
        currentClub.setClubDescription(descUpdateInputData.getNewDescription());
    }
}
