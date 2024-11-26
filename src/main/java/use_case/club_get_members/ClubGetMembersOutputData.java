package use_case.club_get_members;


import entity.data_structure.DataStore;
import entity.user.Student;

/**
 * Output data for the get members use case.
 */
public class ClubGetMembersOutputData {
    private final String email;
    private final DataStore<Student> members;
    private final boolean useCaseFailed;

    public ClubGetMembersOutputData(String email, DataStore<Student> members, boolean useCaseFailed) {
        this.email = email;
        this.members = members;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }

    public DataStore<Student> getMembers() {
        return members;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
