package data_access;

import entity.user.Club;
import entity.user.Student;
import entity.user.User;

import use_case.club_login.ClubLoginDataAccessInterface;
import use_case.signup.club_signup.ClubSignupUserDataAccessInterface;
import use_case.signup.student_signup.StudentSignupUserDataAccessInterface;
import use_case.student_login.StudentLoginDataAccessInterface;

import java.util.ArrayList;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class UserDataAccessObject implements ClubSignupUserDataAccessInterface, StudentSignupUserDataAccessInterface,
        ClubLoginDataAccessInterface, StudentLoginDataAccessInterface {

    private final ArrayList<Club> clubs = new ArrayList<>();
    private final ArrayList<Student> students = new ArrayList<>();

    @Override
    public boolean existsByName(String identifier) {
        for (User club : clubs) {
            if (club.getUsername().equals(identifier)) {
                return true;
            }
        }
        for (User student : students) {
            if (student.getUsername().equals(identifier)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean existsByEmail(String identifier) {
        for (User club : clubs) {
            if (club.getEmail().equals(identifier)) {
                return true;
            }
        }
        for (User student : students) {
            if (student.getEmail().equals(identifier)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void saveClub(User club) {
        clubs.add((Club) club);
    }

    @Override
    public void saveStudent(User student) {
        students.add((Student) student);
    }

    @Override
    public Club getClub(String email) { // TODO use a map or something else to search stuff easier
        for (Club club : clubs) {
            if (club.getEmail().equals(email)) {
                return club;
            }
        }
        return null;
    }

    @Override
    public Student getStudent(String username) {
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }
}
