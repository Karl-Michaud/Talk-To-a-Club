package entities.post;

/**
 * Post factory interface.
 */
public interface PostFactory {
    /**
     * Create a new Post.
     * @param title title of the post
     * @param content contents of the post.
     * @return the new post
     */
    Post create(String title, String content);
}
