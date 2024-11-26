package use_case.club_get_posts;

import entity.data_structure.DataStore;
import entity.post.Post;

/**
 * The output data for the Club Get Posts use case.
 */
public class ClubGetPostsOutputData {

    private final String message;
    private final DataStore<Post> clubPosts;

    public ClubGetPostsOutputData(String message, DataStore<Post> clubPosts) {
        this.message = message;
        this.clubPosts = clubPosts;
    }

    public String getMessage() {
        return this.message;
    }

    public DataStore<Post> getClubPosts() {
        return this.clubPosts;
    }
}
