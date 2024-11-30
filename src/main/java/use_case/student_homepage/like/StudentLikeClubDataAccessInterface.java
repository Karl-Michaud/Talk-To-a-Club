package use_case.student_homepage.like;

import java.util.ArrayList;

import entity.post.Post;
import entity.user.Club;

/**
 * The DAO interface related to club data for the like usecase.
 */
public interface StudentLikeClubDataAccessInterface {

    /**
     * Returns the club with the given email.
     * @param clubEmail the club's email.
     * @return the club with the given email.
     */
    Club getClub(String clubEmail);

    /**
     * Changes the like status in the post being interacted with (either likes if not previously liked, or unlikes if
     * liked), and saves the updated post to the db.
     * @param club The club which the post belongs to.
     * @param post The post which is being liked/ unliked.
     */
    void savePost(Post post, Club club);

    /**
     * Gets the posts of a given club.
     * @param club the club
     */
    ArrayList<Post> getPosts(Club club);
}
