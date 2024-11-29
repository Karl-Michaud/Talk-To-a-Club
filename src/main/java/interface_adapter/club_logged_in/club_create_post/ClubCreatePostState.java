package interface_adapter.club_logged_in.club_create_post;

import java.time.LocalDate;
import java.time.LocalTime;

import interface_adapter.club_logged_in.ClubLoggedInState;

/**
 * Create Post state.
 */
public class ClubCreatePostState extends ClubLoggedInState {
    private String email;
    private String title;
    private String content;
    private LocalTime timeOfPosting;
    private LocalDate dateOfPosting;
    private String createPostError;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalTime getTimeOfPosting() {
        return timeOfPosting;
    }

    public void setTimeOfPosting(LocalTime timeOfPosting) {
        this.timeOfPosting = timeOfPosting;
    }

    public LocalDate getDateOfPosting() {
        return dateOfPosting;
    }

    public void setDateOfPosting(LocalDate dateOfPosting) {
        this.dateOfPosting = dateOfPosting;
    }

    public String getCreatePostError() {
        return createPostError;
    }

    public void setCreatePostError(String createPostError) {
        this.createPostError = createPostError;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "CreatePostState{"
                + "title='" + title + '\''
                + ", content='" + content + '\''
                + ", timeOfPosting='" + timeOfPosting + '\''
                + ", dateOfPosting='" + dateOfPosting + '\''
                + '}';
    }
}
