package interface_adapter.club_logged_in.club_get_members;

import use_case.club_get_members.ClubGetMembersInputBoundary;
import use_case.club_get_members.ClubGetMembersInputData;

/**
 * The controller for the get members use case for clubs.
 */
public class ClubGetMembersController {
    private final ClubGetMembersInputBoundary getMembersInteractor;

    public ClubGetMembersController(ClubGetMembersInputBoundary getMembersInteractor) {
        this.getMembersInteractor = getMembersInteractor;
    }

    /**
     * Executes the get members use case for clubs.
     * @param clubEmail the club email
     */
    public void execute(String clubEmail) {
        final ClubGetMembersInputData inputData = new ClubGetMembersInputData(clubEmail);
        getMembersInteractor.execute(inputData);
    }
}
