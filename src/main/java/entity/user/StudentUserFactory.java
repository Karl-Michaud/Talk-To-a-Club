package entity.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Factory for creating Student user objects.
 */
public class StudentUserFactory implements StudentFactory {

    @Override
    public User create(String name, String email, String password) {
        // new users that are part of no clubs.
        final Map<Integer, Club> joinedClubs = new HashMap<>();
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
    public User create(String name, String email, String password, Map<Integer, Club> joinedClubs) {
        return new Student(name, email, password, joinedClubs);
    }

}
