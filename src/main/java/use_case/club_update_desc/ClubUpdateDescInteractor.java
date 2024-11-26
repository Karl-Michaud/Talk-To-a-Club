package use_case.club_update_desc;

import entity.user.Club;

/**
 * The Club Update Description Interactor.
 */
public class ClubUpdateDescInteractor implements ClubUpdateDescInputBoundary {

    private final ClubUpdateDescDataAccessInterface updateDescDataAccessObject;
    private final ClubUpdateDescOutputBoundary updateDescPresenter;

    public ClubUpdateDescInteractor(ClubUpdateDescDataAccessInterface updateDescDataAccessObject,
                                    ClubUpdateDescOutputBoundary updateDescPresenter) {
        this.updateDescDataAccessObject = updateDescDataAccessObject;
        this.updateDescPresenter = updateDescPresenter;
    }

    @Override
    public void execute(ClubUpdateDescInputData descUpdateInputData) {
        if (!updateDescDataAccessObject.existsByEmailClub(descUpdateInputData.getClubEmail())) {
            // results in a failed message if the club doesn't exist
            updateDescPresenter.prepareMessage("Failure in changing description: Club not Found");
        } else {
            // Gets the current club entity and changes its description to the new one
            Club currentClub = updateDescDataAccessObject.getClub(descUpdateInputData.getClubEmail());
            currentClub.setClubDescription(descUpdateInputData.getNewDescription());
            updateDescPresenter.prepareMessage("Success in changing description.");
        }
    }
}
