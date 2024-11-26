package interface_adapter.student_logged_in.explore_clubs;

import entity.data_structure.DataStore;
import entity.user.Club;
import entity.user.Student;

/**
 * State for Explore clubs use case.
 */
public class ExploreClubsState {
    private DataStore<Club> clubs;
    private String error;
    private Club selectedClub;
    private Student currentStudent;

    public DataStore<Club> getClubs() {
        return clubs;
    }

    public void setClubs(DataStore<Club> clubs) {
        this.clubs = clubs;
    }

    public Student getCurrentStudent() {
        return currentStudent;
    }

    public void setCurrentStudent(Student currentStudent) {
        this.currentStudent = currentStudent;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Club getSelectedClub() {
        return selectedClub;
    }

    public void setSelectedClub(Club selectedClub) {
        this.selectedClub = selectedClub;
    }
}
