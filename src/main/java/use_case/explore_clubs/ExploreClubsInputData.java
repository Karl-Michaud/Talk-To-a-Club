package use_case.explore_clubs;

/**
 * Input data for the get clubs use case.
 */
public class ExploreClubsInputData {
    private final String email;

    // input the logged in student's email.
    public ExploreClubsInputData(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
