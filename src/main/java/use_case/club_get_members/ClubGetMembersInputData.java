package use_case.club_get_members;

/**
 * Input data for the get members use case.
 */
public class ClubGetMembersInputData {
    private final String clubEmail;

    public ClubGetMembersInputData(String clubEmail) {
        this.clubEmail = clubEmail;
    }

    public String getClubEmail() {
        return clubEmail;
    }

}
