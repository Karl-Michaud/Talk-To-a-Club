package interface_adapter.club_home;

/**
 * The state for the Club Home View Model.
 */
public class ClubHomeState {
    private String username = "";
    // TODO add a list instance variable of all posts

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "StudentHomeState{"
                + "username='" + username + '\''
                + '}';
    }
}
