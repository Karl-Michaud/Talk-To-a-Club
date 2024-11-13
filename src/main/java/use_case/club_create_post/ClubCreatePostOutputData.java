package use_case.club_create_post;

/**
 * Output data for post creation use case.
 */
public class ClubCreatePostOutputData {
    private final String title;
    private final String contents;
    private final String timeOfPosting;
    private final String dateOfPosting;
    private final boolean useCaseFailed;

    public ClubCreatePostOutputData(String title, String contents, String timeOfPosting, String dateOfPosting,
                                    boolean useCaseFailed) {
        this.title = title;
        this.contents = contents;
        this.timeOfPosting = timeOfPosting;
        this.dateOfPosting = dateOfPosting;
        this.useCaseFailed = useCaseFailed;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getTimeOfPosting() {
        return timeOfPosting;
    }

    public String getDateOfPosting() {
        return dateOfPosting;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
