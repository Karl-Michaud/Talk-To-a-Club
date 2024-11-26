package use_case.student_homepage.show_posts;

import java.util.List;
import java.util.Map;

/**
 * Class to store the output data for the posts on the studenthome view.
 */
public class ShowPostsOutputData {
    private final Map<String, List<Map<String, Object>>> posts;

    public ShowPostsOutputData(Map<String, List<Map<String, Object>>> posts) {
        this.posts = posts;
    }

    public Map<String, List<Map<String, Object>>> getPosts() {
        return posts;
    }

}
