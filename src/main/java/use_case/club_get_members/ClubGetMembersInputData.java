package use_case.club_get_members;

/**
 * Input data for the get members use case.
 */
public class ClubGetMembersInputData {
    private final String email;

    public ClubGetMembersInputData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
