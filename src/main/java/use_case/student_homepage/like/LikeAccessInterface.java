package use_case.student_homepage.like;

import java.util.Map;

/**
 * Interface for the like usecase.
 */
public interface LikeAccessInterface {
    /**
     * Updates the DB to reflect a student liking a post.
     * @param postData The data of the post which is to be liked/ unliked.
     * @param studentEmail the student liking/unliking the post.
     */
    void likePost(Map<String, Object> postData, String studentEmail);

    /**
     * Updates the DB to reflect a student unliking a post.
     * @param postData The data of the post which is to be liked/ unliked.
     * @param studentEmail the student liking/unliking the post.
     */
    void unLikePost(Map<String, Object> postData, String studentEmail);

    /**
     * @param studentEmail The email of the student for which we query like status for the post.
     * @param postData The data of the post which is being queried for like status.
     * @return True if the user associated with the email has liked the post, and false otherwise.
     */
    Boolean getPostLiked(String studentEmail, Map<String, Object> postData);
}
