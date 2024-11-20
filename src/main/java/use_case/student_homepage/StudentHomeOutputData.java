package use_case.student_homepage;

import java.util.ArrayList;

import entity.user.Club;
import entity.user.User;

/**
 * Class to store the output data for the student home usecase.
 */
public class StudentHomeOutputData {
    private final ArrayList<Club> followedClubs;
    private final User currentUser;

    public StudentHomeOutputData(ArrayList<Club> followedClubs, User currentUser) {
        this.followedClubs = followedClubs;
        this.currentUser = currentUser;
    }

    public ArrayList<Club> getFollowedClubs() {
        return followedClubs;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
