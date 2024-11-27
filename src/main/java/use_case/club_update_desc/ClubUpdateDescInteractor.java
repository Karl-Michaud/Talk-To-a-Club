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
            final String message = "Failure in changing description: Club not Found";
            final ClubUpdateDescOutputData outputData = new ClubUpdateDescOutputData(message, null);
            updateDescPresenter.prepareFailMessage(outputData);
        }
        else {
            // Gets the current club entity and changes its description to the new one, then sends it to the DAO
            final Club currentClub = updateDescDataAccessObject.getClub(descUpdateInputData.getClubEmail());
            final String newDescription = descUpdateInputData.getNewDescription();
            currentClub.setClubDescription(newDescription);
            updateDescDataAccessObject.updateClubDescription(currentClub);

            // Creates an output data with the success message and new description
            final String message = "Success in changing description.";
            final ClubUpdateDescOutputData outputData = new ClubUpdateDescOutputData(message, newDescription);
            updateDescPresenter.prepareSuccessMessage(outputData);
        }
    }
}
