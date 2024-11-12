package data_access;

import entity.user.User;

// import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginDataAccessInterface;
// import use_case.logout.LogoutUserDataAccessInterface;

import java.util.ArrayList;

/**
 * In-memory implementation of the DAO for storing user data. This implementation does
 * NOT persist data between runs of the program.
 */
public class UserDataAccessObject implements SignupUserDataAccessInterface, LoginDataAccessInterface {

    private final ArrayList<User> clubs = new ArrayList<>();
    private final ArrayList<User> students = new ArrayList<>();

    private String currentUsername;

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
    public void saveClub(User user) {
        clubs.add(user);
    }

    @Override
    public void saveStudent(User user) {
        students.add(user);
    }

    @Override
    public User get(String email) { // TODO use a map or something else to search stuff easier
        for (User club : clubs) {
            if (club.getEmail().equals(email)) {
                return club;
            }
        }
        for (User student : students) {
            if (student.getEmail().equals(email)) {
                return student;
            }
        }
        return null;
    }

    @Override
    public void setCurrentUser(String name) {
        this.currentUsername = name;
    }

    @Override
    public String getCurrentUser() {
        return this.currentUsername;
    }
}
