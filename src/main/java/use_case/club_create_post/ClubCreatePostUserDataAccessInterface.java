package use_case.club_create_post;

import entity.post.Post;

/**
 * Data access interface for the post creation use case.
 */
public interface ClubCreatePostUserDataAccessInterface {
    /**
     * Save post to database.
     * @param post the post that needs to be saved
     */
    void savePost(Post post);
}
