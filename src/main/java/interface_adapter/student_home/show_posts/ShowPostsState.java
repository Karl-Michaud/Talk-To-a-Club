package interface_adapter.student_home.show_posts;

import entity.data_structure.DataStoreArrays;
import entity.post.Post;

import java.util.Map;

/**
 * The state for the posts panel.
 */
public class ShowPostsState {
    private Map<String, DataStoreArrays<Post>> posts;
    private String showPostsError;

    public void setPosts(Map<String, DataStoreArrays<Post>> posts) {
        this.posts = posts;
    }

    public Map<String, DataStoreArrays<Post>> getPosts() {
        return posts;
    }

    public void setError(String error) {
        this.showPostsError = error;
    }

    public String getError() {
        return this.showPostsError;
    }
}
