package use_case.student_dislike;

import java.util.ArrayList;

import entity.post.Post;
import entity.user.Club;

/**
 * Interface for the dislike usecase.
 */
public interface StudentDislikeClubDataAccessInterface {
    /**
     * Returns the club with the given email.
     * @param clubEmail the club's email.
     * @return the club with the given email.
     */
    Club getClub(String clubEmail);

    /**
     * Changes the dislike status in the post being interacted with (either likes if not previously liked,
     * or undislikes if
     * PRECONDITION: the club already exists and has been saved before.
     * disliked), and saves the updated post to the db.
     * @param club The club which the post belongs to.
     * @param post The post which is being disliked/ undisliked.
     */
    void savePost(Post post, Club club);

    /**
     * Gets the posts of a given club.
     * @param club the club
     * @return An arraylist of the post objects corresponding to the club.
     */
    ArrayList<Post> getPosts(Club club);
}
