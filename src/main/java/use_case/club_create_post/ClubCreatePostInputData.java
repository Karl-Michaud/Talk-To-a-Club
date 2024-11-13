package use_case.club_create_post;

/**
 * Input data for post creation use case.
 */
public class ClubCreatePostInputData {
    private final String email;
    private final String title;
    private final String content;

    public ClubCreatePostInputData(String email, String title, String description) {
        this.email = email;
        this.title = title;
        this.content = description;
    }

    public String getEmail() {
        return email;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
