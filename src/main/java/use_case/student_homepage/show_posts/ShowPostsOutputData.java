package use_case.student_homepage.show_posts;

import java.util.List;
import java.util.Map;

/**
 * Class to store the output data for the posts on the student home view.
 */
public class ShowPostsOutputData {
    private final Map<String, List<Map<String, Object>>> posts;
    private final String currStudent;

    public ShowPostsOutputData(Map<String, List<Map<String, Object>>> posts, String currStudent) {
        this.posts = posts;
        this.currStudent = currStudent;
    }

    public String getCurrStudent() {
        return currStudent;
    }

    public Map<String, List<Map<String, Object>>> getPostData() {
        return posts;
    }
}
