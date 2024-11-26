package use_case.student_homepage.show_posts;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.Post;

import java.util.Map;

/**
 * Class to store the output data for the posts on the studenthome view.
 */
public class ShowPostsOutputData {
    private final Map<String, DataStoreArrays<Post>> posts;

    public ShowPostsOutputData(Map<String, DataStoreArrays<Post>> posts) {
        this.posts = posts;
    }

    public Map<String, DataStoreArrays<Post>> getPosts() {
        return posts;
    }

}
