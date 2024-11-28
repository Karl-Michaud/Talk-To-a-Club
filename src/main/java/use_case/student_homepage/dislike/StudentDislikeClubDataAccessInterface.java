package use_case.student_homepage.dislike;

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
     * disliked), and saves the updated post to the db.
     * @param club The club which the post belongs to.
     * @param post The post which is being disliked/ undisliked.
     */
    void savePost(Post post, Club club);
}
