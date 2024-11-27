package interface_adapter.student_home.show_posts;

import java.util.List;
import java.util.Map;

/**
 * The state for the posts panel.
 */
public class ShowPostsState {
    private Map<String, List<Map<String, Object>>> posts;
    private String showPostsError;
    private String currentUser;

    public void setPosts(Map<String, List<Map<String, Object>>> posts) {
        this.posts = posts;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public Map<String, List<Map<String, Object>>> getPosts() {
        return posts;
    }

    public void setError(String error) {
        this.showPostsError = error;
    }

    public String getError() {
        return this.showPostsError;
    }
}
