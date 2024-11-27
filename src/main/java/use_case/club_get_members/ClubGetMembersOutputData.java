package use_case.club_get_members;

import entity.data_structure.DataStore;
import entity.user.Student;
import java.util.ArrayList;

/**
 * Output data for the get members use case.
 */
public class ClubGetMembersOutputData {
    private final String email;

    // Notice that for both lists, the element at the i-th index corresponds to one student.
    // Therefore memberEmail.pop() and memberName.pop() will correspond the the information of one student.
    private final ArrayList<String> membersEmail;
    private final ArrayList<String> membersName;

    private final boolean useCaseFailed;

    public ClubGetMembersOutputData(String email, ArrayList<String> membersEmail, ArrayList<String> membersName,
                                    boolean useCaseFailed) {
        this.email = email;
        this.membersEmail = membersEmail;
        this.membersName = membersName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<String> getMembersEmail() {
        return membersEmail;
    }

    public ArrayList<String> getMembersName() {
        return membersName;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
