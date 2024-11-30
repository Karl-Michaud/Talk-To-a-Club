package use_case.club_get_posts;

import java.util.ArrayList;

import entity.data_structure.DataStore;
import entity.user.Club;

/**
 * Interactor for the Club Get Posts Use Case.
 */
public class ClubGetPostsInteractor implements ClubGetPostsInputBoundary {

    private final ClubGetPostsDataAccessInterface clubGetPostsDataAccessObject;
    private final ClubGetPostsOutputBoundary clubGetPostsPresenter;

    public ClubGetPostsInteractor(ClubGetPostsDataAccessInterface clubGetPostsDataAccessObject,
                                  ClubGetPostsOutputBoundary clubGetPostsPresenter) {
        this.clubGetPostsPresenter = clubGetPostsPresenter;
        this.clubGetPostsDataAccessObject = clubGetPostsDataAccessObject;
    }

    @Override
    public void execute(ClubGetPostsInputData clubGetPostsInputData) {
        if (!clubGetPostsDataAccessObject.existsByEmailClub(clubGetPostsInputData.getClubEmail())) {
            // Outputs a failed message if the club doesn't exist and calls the presenter to prepare the fail
            final String message = "Failure Getting Club Posts: Club not Found";
            final ClubGetPostsOutputData clubGetPostsOutputData = new ClubGetPostsOutputData(message,
                    null, null);
            clubGetPostsPresenter.prepareFailMessage(clubGetPostsOutputData);
        }
        else {
            // Gets the current club entity and gets a DataStore of its posts
            final Club currentClub = clubGetPostsDataAccessObject.getClub(clubGetPostsInputData.getClubEmail());
            final DataStore<String> postsTitles = currentClub.getClubPostsTitle();
            final DataStore<String> postsDescriptions = currentClub.getClubPostsDescription();

            /* Stores the posts titles their contents into two arraylists. Calling both lists at the same index
             gives the information for one post. */
            final ArrayList<String> titles = new ArrayList<>();
            final ArrayList<String> descriptions = new ArrayList<>();
            int index = 0;
            while (index < postsTitles.size()) {
                // Get the post data
                final String tite = postsTitles.getByIndex(index);
                final String body = postsDescriptions.getByIndex(index);
                // Populate the array lists
                titles.add(tite);
                descriptions.add(body);
                index++;
            }

            // Creates and Passes the output data to the presenter to display and results in a success message
            final String message = "Success in Getting Club Posts";
            final ClubGetPostsOutputData clubGetPostsOutputData = new ClubGetPostsOutputData(message,
                    titles, descriptions);
            clubGetPostsPresenter.prepareDisplayPosts(clubGetPostsOutputData);
        }
    }
}
