package entity.user;

import java.util.ArrayList;

/**
 * Factory for creating Students.
 */
public interface StudentFactory extends UserFactory {

    @Override
    User create(String name, String email, String password);

    /**
     * Create a new student user.
     * @param name the name of the new student
     * @param email the email of the new student
     * @param password the password of the new student
     * @param joinedClubs the clubs the student has joined
     * @return the new student user
     */
    User create(String name, String email, String password, ArrayList<Club> joinedClubs);
}
