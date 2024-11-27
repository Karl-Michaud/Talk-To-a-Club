package use_case.club_get_posts;

import java.util.List;

/**
 * The output data for the Club Get Posts use case.
 */
public class ClubGetPostsOutputData {

    private final String message;

    private final List<String> postTitles;
    private final List<String> postBodies;

    public ClubGetPostsOutputData(String message, List<String> postTitles, List<String> postBodies) {
        this.message = message;
        this.postTitles = postTitles;
        this.postBodies = postBodies;
    }

    public String getMessage() {
        return this.message;
    }

    public List<String> getPostTitles() {
        return this.postTitles;
    }

    public List<String> getPostBodies() {
        return this.postBodies;
    }
}
