package use_case.student_homepage.dislike;

import java.util.Map;

/**
 * Interface for the dislike usecase.
 */
public interface DislikeAccessInterface {
    /**
     * Updates the DB to reflect a student disliking a post.
     * @param postData The data of the post which is to be disliked.
     * @param studentEmail the student disliking the post.
     */
    void dislikePost(Map<String, Object> postData, String studentEmail);

    /**
     * Updates the DB to reflect a student undisliking a post.
     * @param postData The data of the post which is to be undisliked.
     * @param studentEmail the student undisliking the post.
     */
    void unDislikePost(Map<String, Object> postData, String studentEmail);

    /**
     * @param studentEmail The email of the student for which we query dislike status for the post.
     * @param postData The data of the post which is being queried for dislike status.
     * @return True if the user associated with the email has disliked the post, and false otherwise.
     */
    Boolean getPostDisliked(String studentEmail, Map<String, Object> postData);
}
