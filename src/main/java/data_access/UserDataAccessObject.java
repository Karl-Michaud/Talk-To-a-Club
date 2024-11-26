package data_access;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
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
import use_case.student_homepage.show_posts.ShowPostsAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class UserDataAccessObject implements ClubSignupUserDataAccessInterface, StudentSignupUserDataAccessInterface,
        ClubLoginDataAccessInterface, StudentLoginDataAccessInterface, ClubCreatePostUserDataAccessInterface,
        StudentHomeAccessInterface, ShowPostsAccessInterface {

    private final DataStoreArrays<Student> studentArrayList = new DataStoreArrays<>();
    private final DataStoreArrays<Club> clubArrayList = new DataStoreArrays<>();

    @Override
    public boolean existsByNameClub(String identifier) {
        boolean found = false;
        for (Club club : clubArrayList) {
            if (club.getUsername().equals(identifier)) {
                found = true;
                break;
            }
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
        for (Club club : clubArrayList) {
            if (club.getEmail().equals(identifier)) {
                found = true;
                break;
            }
        }
        return found;
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
        clubArrayList.add(club);
    }

    @Override
    public void saveStudent(Student student) {
        studentArrayList.add(student);
    }

    @Override
    public Club getClub(String email) {
        Club clubFound = null;
        for (Club club : clubArrayList) {
            if (club.getEmail().equals(email)) {
                clubFound = club;
            }
        }
        // This should not be returned as null since the precondition states that the club must exist.
        return clubFound;
    }

    @Override
    public Student getStudent(String email) {
        Student foundStudent = null;
        for (Student student : studentArrayList) {
            if (student.getEmail().equals(email)) {
                foundStudent = student;
            }
        }
        // This should not be returned as null since the precondition states that the student must exist.
        return foundStudent;
    }

    @Override
    public void savePost(Post post, Club club) {
        for (Club current: clubArrayList) {
            if (current.getUsername().equals(club.getUsername())) {
                current.addClubPost(post);
            }
        }
    }
}
