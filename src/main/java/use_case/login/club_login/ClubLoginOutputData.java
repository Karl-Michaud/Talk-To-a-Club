package use_case.login.club_login;

import entity.data_structure.DataStore;
import entity.post.Post;

/**
 * Output data for the login use case for clubs.
 */
public class ClubLoginOutputData {
    private final String username;
    private final String email;
    private final DataStore<Post> posts;
    private final boolean useCaseFailed;

    public ClubLoginOutputData(String username, String email, DataStore<Post> posts, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.posts = posts;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public DataStore<Post> getPosts() {
        return posts;
    }

    /**
     * Returns a value that tells us if the use case failed or not.
     * @return a boolean value true if the use case failed, else false
     */
    public boolean useCaseFailed() {
        return useCaseFailed;
    }
}
