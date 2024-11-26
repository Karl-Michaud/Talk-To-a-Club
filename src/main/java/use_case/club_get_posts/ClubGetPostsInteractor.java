package use_case.club_get_posts;

import entity.user.Club;

public class ClubGetPostsInteractor implements ClubGetPostsInputBoundary {

    private final ClubGetPostsOutputBoundary clubGetPostsPresenter;
    private final ClubGetPostsDataAccessInterface clubGetPostsDataAccessObject;

    public ClubGetPostsInteractor(ClubGetPostsOutputBoundary clubGetPostsPresenter,
                                  ClubGetPostsDataAccessInterface clubGetPostsDataAccessObject) {
        this.clubGetPostsPresenter = clubGetPostsPresenter;
        this.clubGetPostsDataAccessObject = clubGetPostsDataAccessObject;
    }

    @Override
    public void execute(ClubGetPostsInputData clubGetPostsInputData) {
        if (!clubGetPostsDataAccessObject.existsByEmailClub(clubGetPostsInputData.getClubEmail())) {
            // results in a failed message if the club doesn't exist
            String message = "Failure Getting Club Posts: Club not Found";
            ClubGetPostsOutputData clubGetPostsOutputData = new ClubGetPostsOutputData(message, null);
            clubGetPostsPresenter.prepareFailMessage(clubGetPostsOutputData);
        } else {
            // Gets the current club entity and creates an output data with its clubPosts
            Club currentClub = clubGetPostsDataAccessObject.getClub(clubGetPostsInputData.getClubEmail());
            String message = "Success in Getting Club Posts";
            ClubGetPostsOutputData clubGetPostsOutputData = new ClubGetPostsOutputData(message,
                    currentClub.getClubPosts());

            // Passes the output data to the presenter to display and results in a success message
            clubGetPostsPresenter.prepareDisplayPosts(clubGetPostsOutputData);
        }
    }
}
