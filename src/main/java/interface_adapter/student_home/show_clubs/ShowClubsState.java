package interface_adapter.student_home.show_clubs;

import java.util.List;

/**
 * The state for the clubs panel.
 */
public class ShowClubsState {
    private List<String> clubNames;
    private String currentUser;
    private String showClubsError;

    public void setClubNames(List<String> clubNames) {
        this.clubNames = clubNames;
    }

    public void setCurrentUser(String currentUser) {
        this.currentUser = currentUser;
    }

    public void setShowClubsError(String showClubsError) {
        this.showClubsError = showClubsError;
    }

    public List<String> getClubNames() {
        return clubNames;
    }

    public String getCurrentUser() {
        return currentUser;
    }

    public String getShowClubsError() {
        return showClubsError;
    }
}
