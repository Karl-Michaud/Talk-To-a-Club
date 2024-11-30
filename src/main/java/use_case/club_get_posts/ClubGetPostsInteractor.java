package use_case.club_get_posts;

import java.util.ArrayList;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.Post;
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

            /* Stores the posts titles their contents into two arraylists, then stores them in a DataStore object.
            (Calling both lists at the same index gives the information for one post.) */
            final DataStore<Post> posts = currentClub.getClubPosts();
            final ArrayList<String> postTitles = new ArrayList<>();
            final ArrayList<String> postBodies = new ArrayList<>();
            for (Post post: (DataStoreArrays<Post>) posts) {
                postTitles.add(post.getTitle());
                postBodies.add(post.getContent());
            }

            // Creates and Passes the output data to the presenter to display and results in a success message
            final String message = "Success in Getting Club Posts";
            final ClubGetPostsOutputData clubGetPostsOutputData = new ClubGetPostsOutputData(message,
                    postTitles, postBodies);
            clubGetPostsPresenter.prepareDisplayPosts(clubGetPostsOutputData);
        }
    }
}
