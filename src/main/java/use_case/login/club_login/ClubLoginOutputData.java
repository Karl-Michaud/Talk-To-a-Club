package use_case.login.club_login;

/**
 * Output data for the login use case for clubs.
 */
public class ClubLoginOutputData {
    private final String username;
    private final String email;
    private final String description;
    private final boolean useCaseFailed;

    public ClubLoginOutputData(String username, String email, String description, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.description = description;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Returns a value that tells us if the use case failed or not.
     * @return a boolean value true if the use case failed, else false
     */
    public boolean useCaseFailed() {
        return useCaseFailed;
    }
}
