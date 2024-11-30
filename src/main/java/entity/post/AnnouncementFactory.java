package entity.post;

import entity.data_structure.DataStoreArrays;

/**
 * Factory for creating new Announcement posts.
 */
public class AnnouncementFactory implements PostFactory {

    /**
     * Create a new Post.
     * @param title title of the post
     * @param content contents of the post.
     * @return the new post
     */
    public Post create(String title, String content) {
        return new Announcement(title, content, new DataStoreArrays<>(), new DataStoreArrays<>());
    }
}
