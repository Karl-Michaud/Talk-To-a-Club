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
        final DataStoreArrays<String> joinedClubsEmails = new DataStoreArrays<>();
        final DataStoreArrays<String> joinedClubsNames = new DataStoreArrays<>();
        return new Student(name, email, password, joinedClubsEmails, joinedClubsNames);
    }

    @Override
    public Student create(String name, String email, String password, DataStore<String> joinedClubsEmails,
                          DataStore<String> joinedClubsNames) {
        return new Student(name, email, password, joinedClubsEmails, joinedClubsNames);
    }

}
