package use_case.club_get_posts;

/**
 * The Input Data for the Club Get Posts Use Case.
 */
public class ClubGetPostsInputData {

    private final String clubEmail;

    public ClubGetPostsInputData(String clubEmail) {
        this.clubEmail = clubEmail;
    }

    public String getClubEmail() {
        return this.clubEmail;
    }
}
