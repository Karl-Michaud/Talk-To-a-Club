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

    private final ArrayList<Student> studentArrayList = new ArrayList<>();
    private final ArrayList<Club> clubArrayList = new ArrayList<>();
    private final ArrayList<ArrayList<Post>> postArrayList = new ArrayList<>();

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
        final ArrayList<Club> joinedClubs = new ArrayList<>();
        for (Club club : clubArrayList) {
            if (club.getClubMembersEmails().contains(student.getEmail())) {
                joinedClubs.add(club);
            }
        }
        return joinedClubs;
    }

    @Override
    public ArrayList<Post> getPosts(Club club) {
        final int index = clubArrayList.indexOf(club);
        return postArrayList.get(index);
    }

    @Override
    public boolean existsByNameClub(String identifier) {
        boolean exists = false;
        for (Club club : clubArrayList) {
            if (club.getUsername().equals(identifier)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public boolean existsByNameStudent(String username) {
        boolean exists = false;
        for (Student student : studentArrayList) {
            if (student.getUsername().equals(username)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public boolean existsByEmailClub(String identifier) {
        boolean exists = false;
        for (Club club : clubArrayList) {
            if (club.getEmail().equals(identifier)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public void updateClubDescription(Club club) {
        for (Club currClub : clubArrayList) {
            if (currClub.getClubMembersEmails().contains(club.getEmail())) {
                currClub.setClubDescription(club.getClubDescription());
                break;
            }
        }
    }

    @Override
    public boolean existsByEmailStudent(String identifier) {
        boolean exists = false;
        for (Student student : studentArrayList) {
            if (student.getEmail().equals(identifier)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    @Override
    public void saveClub(Club club) {
        // Please note
        boolean found = false;
        for (Club currClub : clubArrayList) {
            if (currClub.getEmail().equals(club.getEmail())) {
                final int index = clubArrayList.indexOf(currClub);
                clubArrayList.remove(currClub);
                clubArrayList.add(index, club);
                found = true;
            }
        }
        if (!found) {
            // Since clubs and posts have the same index. Thus, if the club didn't exist before, add both at the end of
            // their respective lists.
            clubArrayList.add(club);
            postArrayList.add(new ArrayList<>());
        }
    }

    @Override
    public void saveStudent(Student student) {
        studentArrayList.remove(student);
        studentArrayList.add(student);
    }

    @Override
    public Club getClub(String email) {
        Club clubFound = null;
        for (Club currClub : clubArrayList) {
            if (currClub.getEmail().equals(email)) {
                clubFound = currClub;
                break;
            }
        }
        return clubFound;
    }

    @Override
    public void updateClubMembers(Club club) {
        saveClub(club);
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
        studentArrayList.remove(student);
        studentArrayList.add(student);
    }

    @Override
    public void savePost(Post post, Club club) {
        // Since this is only called when the club is logged in, we do not have to worry about the indexOf(club)
        // being -1.

        // IMPORTANT NOTE: IN TESTS, SAVE POST CAN ONLY BE CALLED AFTER THE CLUB IS SAVED. THE REASON BEHIND THAT IS
        // THAT THE METHOD IS ONLY CALLED IN LOGGED IN USE CASES, MEANING THE CLUB EXISTS IN THE DB. CHECK METHOD
        // PRECONDITION.

        final int index = clubArrayList.indexOf(club);
        postArrayList.get(index).remove(post);
        postArrayList.get(index).add(post);
        saveClub(club);
    }

    @Override
    public DataStore<Club> getAllClubs() {
        final DataStore<Club> allClubs = new DataStoreArrays<>();
        for (Club club : clubArrayList) {
            allClubs.add(club);
        }
        return allClubs;
    }
}
