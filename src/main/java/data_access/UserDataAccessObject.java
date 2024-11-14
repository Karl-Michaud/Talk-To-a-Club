package data_access;

import java.util.ArrayList;

import entity.user.Club;
import entity.user.Student;
import entity.user.User;
import use_case.login.club_login.ClubLoginDataAccessInterface;
import use_case.signup.club_signup.ClubSignupUserDataAccessInterface;
import use_case.signup.student_signup.StudentSignupUserDataAccessInterface;
import use_case.login.student_login.StudentLoginDataAccessInterface;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class UserDataAccessObject implements ClubSignupUserDataAccessInterface, StudentSignupUserDataAccessInterface,
        ClubLoginDataAccessInterface, StudentLoginDataAccessInterface {

    private final ArrayList<Club> clubs = new ArrayList<>();
    private final ArrayList<Student> students = new ArrayList<>();

    // TODO Can we ignore the checkstyle error: return count is 2 (max for non-void is 1)
    @Override
    public boolean existsByName(String identifier) {
        boolean found = false;
        for (User student : students) {
            if (student.getUsername().equals(identifier)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public boolean existsByEmail(String identifier) {
        boolean found = false;
        for (User club : clubs) {
            if (club.getEmail().equals(identifier)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public void saveClub(User club) {
        clubs.add((Club) club);
    }

    @Override
    public void saveStudent(User student) {
        students.add((Student) student);
    }

    // TODO use a map or something else to search stuff easier
    @Override
    public Club getClub(String email) {
        Club foundClub = null;
        for (Club club : clubs) {
            if (club.getEmail().equals(email)) {
                foundClub = club;
            }
        }
        return foundClub;
    }

    // TODO Checkstyle doesn't like early returns????
    @Override
    public Student getStudent(String username) {
        Student foundStudent = null;
        for (Student student : students) {
            if (student.getUsername().equals(username)) {
                foundStudent = student;
            }
        }
        return foundStudent;
    }
}
