package data_access;

import java.util.ArrayList;

import entity.post.Post;
import entity.user.Club;
import entity.user.Student;
import entity.user.User;
import use_case.club_create_post.ClubCreatePostUserDataAccessInterface;
import use_case.login.club_login.ClubLoginDataAccessInterface;
import use_case.signup.club_signup.ClubSignupUserDataAccessInterface;
import use_case.signup.student_signup.StudentSignupUserDataAccessInterface;
import use_case.login.student_login.StudentLoginDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class UserDataAccessObject implements ClubSignupUserDataAccessInterface, StudentSignupUserDataAccessInterface,
        ClubLoginDataAccessInterface, StudentLoginDataAccessInterface, ClubCreatePostUserDataAccessInterface {

//    private final ArrayList<Club> clubs = new ArrayList<>();
//    private final ArrayList<Student> students = new ArrayList<>();
    private final ArrayList<User> userArrayList = new ArrayList<>();

    // TODO Can we ignore the checkstyle error: return count is 2 (max for non-void is 1)
    @Override
    public boolean existsByName(String identifier) {
        boolean found = false;
        for (User user : userArrayList) {
            if (user.getUsername().equals(identifier)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public boolean existsByEmail(String identifier) {
        boolean found = false;
        for (User user: userArrayList) {
            if (user.getEmail().equals(identifier)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public void saveClub(User club) {
        userArrayList.add((Club) club);
    }

    @Override
    public void saveStudent(User student) {
        userArrayList.add((Student) student);
    }

    // TODO use a map or something else to search stuff easier
    @Override
    public Club getClub(String email) {
        Club foundClub = null;
        for (User user : userArrayList) {
            if (user.getEmail().equals(email)) {
                foundClub = (Club) user;
            }
        }
        return foundClub;
    }

    // TODO Checkstyle doesn't like early returns????
    @Override
    public Student getStudent(String email) {
        Student foundStudent = null;
        for (User user : userArrayList) {
            if (user.getEmail().equals(email)) {
                foundStudent = (Student) user;
            }
        }
        return foundStudent;
    }

    @Override
    public void savePost(Post post, Club club) {
        // TODO: Implement the body of this method
    }
}
