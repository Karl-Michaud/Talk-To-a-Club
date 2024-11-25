package use_case.club_create_post;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Output data for post creation use case.
 */
public class ClubCreatePostOutputData {
    private final String title;
    private final String contents;
    private final LocalTime timeOfPosting;
    private final LocalDate dateOfPosting;
    private final boolean useCaseFailed;

    public ClubCreatePostOutputData(String title, String contents, LocalTime timeOfPosting, LocalDate dateOfPosting,
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

    public LocalTime getTimeOfPosting() {
        return timeOfPosting;
    }

    public LocalDate getDateOfPosting() {
        return dateOfPosting;
    }

    /**
     * Returns a value that tells us if the use case failed or not.
     * @return a boolean value true if the use case failed, else false
     */
    public boolean useCaseFailed() {
        return useCaseFailed;
    }
}
