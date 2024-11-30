package entity.user;

import entity.data_structure.DataStore;

/**
 * Factory for creating Students.
 */
public interface StudentFactory extends UserFactory {

    @Override
    Student create(String name, String email, String password);

    /**
     * Create a new student user.
     * @param name the name of the new student
     * @param email the email of the new student
     * @param password the password of the new student
     * @param joinedClubsEmails the emails of the clubs joined
     * @param joinedClubsNames the names of the clubs joined
     * @return the new student user
     */
    Student create(String name, String email, String password, DataStore<String> joinedClubsEmails,
                   DataStore<String> joinedClubsNames);
}
