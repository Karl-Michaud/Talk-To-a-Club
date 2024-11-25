package entity.user;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;

/**
 * Factory for creating Student user objects.
 */
public class StudentUserFactory implements StudentFactory {

    @Override
    public Student create(String name, String email, String password) {
        // new users that are part of no clubs.
        final DataStoreArrays<Club> joinedClubs = new DataStoreArrays<>();
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
    public Student create(String name, String email, String password, DataStore<Club> joinedClubs) {
        return new Student(name, email, password, joinedClubs);
    }

}
