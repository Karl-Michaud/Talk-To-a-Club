package use_case.club_get_posts;

import java.util.Map;

/**
 * The output data for the Club Get Posts use case.
 */
public class ClubGetPostsOutputData {

    private final String message;
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
