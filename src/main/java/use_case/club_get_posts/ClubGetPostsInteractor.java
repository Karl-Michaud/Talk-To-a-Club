package use_case.club_get_posts;

import java.util.HashMap;
import java.util.Map;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;

/**
 * Interactor for the Club Get Posts Use Case.
 */
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
            final String message = "Failure Getting Club Posts: Club not Found";
            final ClubGetPostsOutputData clubGetPostsOutputData = new ClubGetPostsOutputData(message, null);
            clubGetPostsPresenter.prepareFailMessage(clubGetPostsOutputData);
        }
        else {
            // Gets the current club entity and gets a DataStore of its posts
            final Club currentClub = clubGetPostsDataAccessObject.getClub(clubGetPostsInputData.getClubEmail());

            // Stores the posts titles as keys and their contents as values in a Hashmap
            final Map<String, String> postsMap = new HashMap<>();
            final DataStore<Post> posts = currentClub.getClubPosts();
            for (Post post: (DataStoreArrays<Post>) posts) {
                postsMap.put(post.getTitle(), post.getContent());
            }

            // Passes the output data to the presenter to display and results in a success message
            final String message = "Success in Getting Club Posts";
            final ClubGetPostsOutputData clubGetPostsOutputData = new ClubGetPostsOutputData(message, postsMap);
            clubGetPostsPresenter.prepareDisplayPosts(clubGetPostsOutputData);
        }
    }
}
