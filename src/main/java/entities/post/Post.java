package entities.post;

import entities.user.User;

/**
 * Post interface. All posts must implement the following interface.
 */
public interface Post {
    /**
     * Returns the title of the post.
     * @return String title of the post
     */
    String getTitle();

    /**
     * Returns the contents of the post.
     * @return String content of the post
     */
    String getContent();

    /**
     * Returns the identification of the post.
     * @return int post ID
     */
    int getPostID();

    /**
     * Sets the identification of the post.
     * @param postId post's identification
     */
    void setPostID(int postId);

    /**
     * Returns the number of likes.
     * @return int number of likes.
     */
    int numberOfLikes();

    /**
     * User likes the post.
     * @param user user that likes the post.
     */
    void addLike(User user);

    /**
     * User unlikes the post.
     * @param user that unlikes the post
     */
    void removeLike(User user);

    /**
     * User dislikes the post.
     * @param user that dislikes the post
     */
    void addDislike(User user);

    /**
     * User removes dislike from post.
     * @param user that wants to remove dislike
     */
    void removeDislike(User user);

    /**
     * Number of dislikes for given post.
     * @return int the number of dislikes
     */
    int numberOfDislikes();

    /**
     * Returns the date of publication of the post.
     * @return String the date of publication of the post
     */
    String dateOfPosting();

    /**
     * Returns the time of publication of the post.
     * @return String the time of the publication of the post
     */
    String timeOfPosting();
}
