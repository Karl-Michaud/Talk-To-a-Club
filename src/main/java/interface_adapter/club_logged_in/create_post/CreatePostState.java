package interface_adapter.club_logged_in.create_post;

import interface_adapter.club_logged_in.ClubLoggedInState;

/**
 * Create Post state.
 */
public class CreatePostState extends ClubLoggedInState {
    private String title;
    private String content;
    private String timeOfPosting;
    private String dateOfPosting;
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

    public String getTimeOfPosting() {
        return timeOfPosting;
    }

    public void setTimeOfPosting(String timeOfPosting) {
        this.timeOfPosting = timeOfPosting;
    }

    public String getDateOfPosting() {
        return dateOfPosting;
    }

    public void setDateOfPosting(String dateOfPosting) {
        this.dateOfPosting = dateOfPosting;
    }

    public String getCreatePostError() {
        return createPostError;
    }

    public void setCreatePostError(String createPostError) {
        this.createPostError = createPostError;
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
