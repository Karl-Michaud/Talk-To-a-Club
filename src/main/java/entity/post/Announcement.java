package entity.post;

import java.time.LocalDate;
import java.time.LocalTime;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.user.User;

/**
 * Announcement class that implements the Post interface. Announcement is a type of post.
 * For now, announcement is the only type of Post.
 */
public class Announcement implements Post {
    // Text information of post
    private String title;
    private String content;

    // Date/time information of post
    private LocalTime timeOfPosting;
    private LocalDate dateOfPosting;

    // Like/Dislike information of post
    private DataStore<String> userLiked;
    private DataStore<String> userDisliked;

    public Announcement(String title, String content, DataStore<String> userLiked, DataStore<String> userDisliked) {
        this.title = title;
        this.content = content;
        this.userLiked = userLiked;
        this.userDisliked = userDisliked;
        this.timeOfPosting = LocalTime.now();
        this.dateOfPosting = LocalDate.now();
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    /**
     * Returns the date of publication of the post.
     * @return String the date of publication of the post
     */
    public LocalDate dateOfPosting() {
        return dateOfPosting;
    }

    /**
     * Returns the time of publication of the post.
     * @return String the time of the publication of the post
     */
    public LocalTime timeOfPosting() {
        return timeOfPosting;
    }

    /**
     * Returns the number of likes.
     * @return int number of likes.
     */
    public int numberOfLikes() {
        return userLiked.size();
    }

    /**
     * Number of dislikes for given post.
     * @return int the number of dislikes
     */
    public int numberOfDislikes() {
        return userDisliked.size();
    }

    @Override
    public DataStore<String> getLikes() {
        return this.userDisliked;
    }

    @Override
    public DataStore<String> getDislikes() {
        return this.userDisliked;
    }

    /**
     * User likes the post.
     * @param user user that likes the post.
     */
    public void addLike(User user) {
        userLiked.add(user.getEmail());
    }

    /**
     * User unlikes the post.
     * @param user that unlikes the post
     */
    public void removeLike(User user) {
        userLiked.remove(user.getEmail());
    }

    /**
     * User dislikes the post.
     * @param user that dislikes the post
     */
    public void addDislike(User user) {
        userDisliked.add(user.getEmail());
    }

    /**
     * User removes dislike from post.
     * @param user that wants to remove dislike
     */
    public void removeDislike(User user) {
        userDisliked.remove(user.getEmail());
    }

}
