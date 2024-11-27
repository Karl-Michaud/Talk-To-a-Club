package use_case.club_get_posts;

import java.util.Map;

/**
 * The output data for the Club Get Posts use case.
 */
public class ClubGetPostsOutputData {

    private final String message;
    // The key in this map is a post's title and the value is its body
    private final Map<String, String> posts;

    public ClubGetPostsOutputData(String message, Map<String, String> posts) {
        this.message = message;
        this.posts = posts;
    }

    public String getMessage() {
        return this.message;
    }

    public Map<String, String> getPosts() {
        return this.posts;
    }
}
