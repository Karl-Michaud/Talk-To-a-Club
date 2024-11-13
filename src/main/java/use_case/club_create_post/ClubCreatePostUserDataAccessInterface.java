package use_case.club_create_post;

import entity.post.Post;
import entity.user.Club;

/**
 * Data access interface for the post creation use case.
 */
public interface ClubCreatePostUserDataAccessInterface {
    /**
     * Save post to database. The specific post will be stored with the data of the given club.
     * @param post the post that needs to be saved
     * @param club the club that posted
     */
    void savePost(Post post, Club club);

    /**
     * Get the club with the given email.
     * @param email the email of the club
     * @return the club with matching email
     */
    Club getClub(String email);
}
