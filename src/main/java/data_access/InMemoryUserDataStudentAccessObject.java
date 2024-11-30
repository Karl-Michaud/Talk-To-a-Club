package data_access;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;
import entity.user.Student;
import use_case.club_create_post.ClubCreatePostUserDataAccessInterface;
import use_case.club_get_members.ClubGetMembersUserDataAccessInterface;
import use_case.club_get_posts.ClubGetPostsDataAccessInterface;
import use_case.club_remove_member.ClubRemoveMemberClubDataAccessInterface;
import use_case.club_remove_member.ClubRemoveMemberStudentDataAccessInterface;
import use_case.club_update_desc.ClubUpdateDescDataAccessInterface;
import use_case.login.club_login.ClubLoginDataAccessInterface;
import use_case.login.student_login.StudentLoginDataAccessInterface;
import use_case.signup.club_signup.ClubSignupUserDataAccessInterface;
import use_case.signup.student_signup.StudentSignupUserDataAccessInterface;
import use_case.student_homepage.dislike.StudentDislikeClubDataAccessInterface;
import use_case.student_homepage.dislike.StudentDislikeStudentDataAccessInterface;
import use_case.student_homepage.like.StudentLikeClubDataAccessInterface;
import use_case.student_homepage.like.StudentLikeStudentDataAccessInterface;
import use_case.student_homepage.show_clubs.StudentShowClubsAccessInterface;
import use_case.student_homepage.show_posts.StudentShowPostsClubAccessInterface;
import use_case.student_homepage.show_posts.StudentShowPostsStudentAccessInterface;
import use_case.explore_clubs.StudentExploreClubsDataAccessInterface;
import use_case.explore_clubs.ClubExploreClubsDataAccessInterface;
import use_case.student_join_club.StudentJoinClubDataAccessInterface;
import use_case.student_join_club.ClubStudentJoinClubDataAccessInterface;
import use_case.student_leave_club.StudentLeaveClubDataAccessInterface;
import use_case.student_leave_club.ClubStudentLeaveClubDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class InMemoryUserDataStudentAccessObject implements ClubSignupUserDataAccessInterface,
        StudentSignupUserDataAccessInterface,
        ClubLoginDataAccessInterface, StudentLoginDataAccessInterface, ClubCreatePostUserDataAccessInterface,
        ClubGetPostsDataAccessInterface, ClubRemoveMemberClubDataAccessInterface,
        ClubRemoveMemberStudentDataAccessInterface,
        ClubUpdateDescDataAccessInterface,
        ClubGetMembersUserDataAccessInterface, StudentShowPostsStudentAccessInterface,
        StudentLikeClubDataAccessInterface,
        StudentLikeStudentDataAccessInterface, StudentDislikeClubDataAccessInterface,
        StudentDislikeStudentDataAccessInterface, StudentShowClubsAccessInterface,
        StudentExploreClubsDataAccessInterface, ClubExploreClubsDataAccessInterface,
        StudentJoinClubDataAccessInterface, ClubStudentJoinClubDataAccessInterface,
        StudentLeaveClubDataAccessInterface, ClubStudentLeaveClubDataAccessInterface,
        StudentShowPostsClubAccessInterface {

    private final DataStoreArrays<Student> studentArrayList = new DataStoreArrays<>();
    private final Map<String, DataStore> clubMap = new HashMap<>();

    public InMemoryUserDataStudentAccessObject() {
        final DataStoreArrays<Club> clubArrayList = new DataStoreArrays<>();
        final DataStoreArrays<ArrayList<Post>> postArrayList = new DataStoreArrays<>();
        clubMap.put("clubs", clubArrayList);
        clubMap.put("posts", postArrayList);
    }

//    public InMemoryUserDataAccessObject() {
//        final Club club = new Club("test", "@.", "123123123", new DataStoreArrays<>(),
//                new DataStoreArrays<>());
//        final Student student = new Student("student", "student@.", "123123123", new DataStoreArrays<>());
//        studentArrayList.add(student);
//        student.joinClub(club);
//        System.out.println(studentArrayList.size());
//        club.addClubMember(student);
//        clubArrayList.add(club);
//
//        // TODO REMOVE THIS AFTER TESTING REMOVE MEMBERS
//    }

    @Override
    public ArrayList<Club> getStudentJoinedClubs(Student student) {
        final DataStore<Club> clubs = clubMap.get("clubs");
        final ArrayList<Club> joinedClubs = new ArrayList<>();
        int index = 0;
        while (index < clubs.size()) {
            final Club currClub = clubs.getByIndex(index);
            if (currClub.getClubMembersEmails().contains(student.getEmail())) {
                joinedClubs.add(currClub);
            }
            index++;
        }
        return joinedClubs;
    }

    @Override
    public ArrayList<Post> getPosts(Club club) {
        final DataStore<ArrayList<Post>> posts = clubMap.get("posts");
        final DataStore<Club> clubs = clubMap.get("clubs");
        ArrayList<Post> clubPosts = null;
        int index = 0;
        while (index < clubs.size()) {
            final String clubEmail = clubs.getByIndex(index).getEmail();
            if (clubEmail.equals(club.getEmail())) {
                clubPosts = posts.getByIndex(index);
                break;
            }
            index++;
        }
        return clubPosts;
    }

    @Override
    public boolean existsByNameClub(String identifier) {
        boolean found = false;
        final DataStore<Club> clubs = clubMap.get("clubs");
        int index = 0;
        while (index < clubs.size()) {
            final Club club = clubs.getByIndex(index);
            if (club.getUsername().equals(identifier)) {
                found = true;
                break;
            }
            index++;
        }
        return found;
    }

    @Override
    public boolean existsByNameStudent(String username) {
        boolean found = false;
        for (Student student : studentArrayList) {
            if (student.getUsername().equals(username)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public boolean existsByEmailClub(String identifier) {
        boolean found = false;
        final DataStore<Club> clubs = clubMap.get("clubs");
        int index = 0;
        while (index < clubs.size()) {
            final Club club = clubs.getByIndex(index);
            if (club.getEmail().equals(identifier)) {
                found = true;
                break;
            }
            index++;
        }
        return found;
    }

    @Override
    public void updateClubDescription(Club club) {
        clubMap.get("clubs").remove(club);
        clubMap.get("clubs").add(club);
    }

    @Override
    public boolean existsByEmailStudent(String identifier) {
        boolean found = false;
        if (!found) {
            for (Student student : studentArrayList) {
                if (student.getEmail().equals(identifier)) {
                    found = true;
                    break;
                }
            }
        }
        return found;
    }

    @Override
    public void saveClub(Club club) {
        clubMap.get("clubs").add(club);
    }

    @Override
    public void saveStudent(Student student) {
        studentArrayList.add(student);
    }

    @Override
    public Club getClub(String email) {
        Club clubFound = null;
        final DataStore<Club> clubs = clubMap.get("clubs");
        int index = 0;
        while (index < clubs.size()) {
            final Club club = clubs.getByIndex(index);
            if (club.getEmail().equals(email)) {
                clubFound = club;
                break;
            }
            index++;
        }
        return clubFound;
    }

    @Override
    public void updateClubMembers(Club club) {
        // The club should already be updated in the in memory model, since the entity objects are stored
        clubMap.get("clubs").remove(club);
        clubMap.get("clubs").add(club);
    }

    @Override
    public Student getStudent(String email) {
        Student foundStudent = null;
        for (Student student : studentArrayList) {
            if (student.getEmail().equals(email)) {
                foundStudent = student;
                break;
            }
        }
        // This should not be returned as null since the precondition states that the student must exist.
        return foundStudent;
    }

    @Override
    public void updateStudentClubsJoined(Student student) {
        this.studentArrayList.remove(student);
        this.studentArrayList.add(student);
    }

    @Override
    public void savePost(Post post, Club club) {
        clubMap.get("posts").remove(post);
        clubMap.get("posts").add(post);
    }

    @Override
    public DataStore<Club> getAllClubs() {
        return clubMap.get("clubs");
    }
}
