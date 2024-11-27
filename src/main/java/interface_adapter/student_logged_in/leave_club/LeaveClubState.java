package interface_adapter.student_logged_in.leave_club;

/**
 * State class for the Leave Club Use Case.
 */
public class LeaveClubState {
    private boolean isMember;
    private String errorMessage;

    public boolean isMember() {
        return isMember;
    }

    public void setMember(boolean member) {
        isMember = member;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
