package data_access;

import java.util.ArrayList;

import entity.post.Post;
import entity.user.Club;
import entity.user.Student;
import entity.user.User;
import use_case.club_create_post.ClubCreatePostUserDataAccessInterface;
import use_case.login.club_login.ClubLoginDataAccessInterface;
import use_case.login.student_login.StudentLoginDataAccessInterface;
import use_case.signup.club_signup.ClubSignupUserDataAccessInterface;
import use_case.signup.student_signup.StudentSignupUserDataAccessInterface;
import use_case.student_homepage.StudentHomeAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class UserDataAccessObject implements ClubSignupUserDataAccessInterface, StudentSignupUserDataAccessInterface,
        ClubLoginDataAccessInterface, StudentLoginDataAccessInterface, ClubCreatePostUserDataAccessInterface,
        StudentHomeAccessInterface {

    private final ArrayList<User> userArrayList = new ArrayList<>();

    // TODO Can we ignore the checkstyle error: return count is 2 (max for non-void is 1)

    /**
     * Verifies if a user with given identifier username exists in the database.
     * @param identifier the username to look for
     * @return true if the user exists with given identifier username
     */
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

    /**
     * Verifies if a user with given identifier email exists in the database.
     * @param identifier the email to look for
     * @return true if the user exists with given identifier email
     */
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

    /**
     * Saves given club user to the database.
     * @param club the club user to save
     */
    @Override
    public void saveClub(User club) {
        userArrayList.add((Club) club);
    }

    /**
     * Save given student user to the database.
     * @param student the student user to save
     */
    @Override
    public void saveStudent(User student) {
        userArrayList.add((Student) student);
    }

    /**
     * Returns club user with corresponding email identifier.
     * @param email of the club we are looking for
     * @return club user with corresponding email identifier
     */
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

    /**
     * Returns student user with corresponding email identifier.
     * @param email of the student we are looking for
     * @return student user with corresponding email identifier
     */
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

    /**
     * Save given post from a given club to the database.
     * @param post the post that needs to be saved
     * @param club the club that posted
     */
    @Override
    public void savePost(Post post, Club club) {
        for (User user : userArrayList) {
            if (user.getEmail().equals(club.getEmail())) {
                final Club isClub = (Club) user;
                isClub.addClubPost(post);
                break;

            }
        }
    }
}
