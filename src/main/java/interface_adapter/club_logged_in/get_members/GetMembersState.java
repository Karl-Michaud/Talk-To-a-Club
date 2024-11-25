package interface_adapter.club_logged_in.get_members;

import entity.data_structure.DataStore;
import entity.user.Student;

/**
 * The state of the data for the club get members use case. This data will be updated by the use case.
 */
public class GetMembersState {
    private String clubEmail;
    private DataStore<Student> members;
    private boolean errorMessage;

    public String getClubEmail() {
        return clubEmail;
    }

    public void setClubEmail(String clubEmail) {
        this.clubEmail = clubEmail;
    }

    public DataStore<Student> getMembers() {
        return members;
    }

    public void setMembers(DataStore<Student> members) {
        this.members = members;
    }

    public boolean getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(boolean errorMessage) {
        this.errorMessage = errorMessage;
    }
}
