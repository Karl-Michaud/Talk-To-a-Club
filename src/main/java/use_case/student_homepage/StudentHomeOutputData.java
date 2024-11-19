package use_case.student_homepage;

import entity.user.Club;

import java.util.ArrayList;

public class StudentHomeOutputData {
    private final ArrayList<Club> followedClubs;

    public StudentHomeOutputData(ArrayList<Club> followedClubs) {
        this.followedClubs = followedClubs;
    }

    public ArrayList<Club> getFollowedClubs() {
        return followedClubs;
    }
}
