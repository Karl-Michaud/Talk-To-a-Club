package entity.user;

import java.util.ArrayList;

/**
 * Factory for creating Student user objects.
 */
public class StudentUserFactory implements StudentFactory {

    @Override
    public Student create(String name, String email, String password) {
        // new users that are part of no clubs.
        final ArrayList<Club> joinedClubs = new ArrayList<>();
        return new Student(name, email, password, joinedClubs);
    }

    /**
     * Create a new student user.
     * @param name the name of the new student
     * @param email the email of the new student
     * @param password the password of the new student
     * @param joinedClubs the clubs the student has joined
     * @return the new student user
     */
    public Student create(String name, String email, String password, ArrayList<Club> joinedClubs) {
        return new Student(name, email, password, joinedClubs);
    }

}
