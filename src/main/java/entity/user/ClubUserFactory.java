package entity.user;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;

/**
 * Factory for creating Club user objets.
 */
public class ClubUserFactory implements ClubFactory {
    @Override
    public Club create(String name, String email, String password) {
        final DataStoreArrays<String> membersEmails = new DataStoreArrays<>();
        final DataStoreArrays<String> membersNames = new DataStoreArrays<>();
        final DataStore<String> clubPostsTitle = new DataStoreArrays<>();
        final DataStore<String> clubPostsDescription = new DataStoreArrays<>();
        return new Club(name, email, password, membersEmails, membersNames, clubPostsTitle, clubPostsDescription);
    }

    @Override
    public Club create(String username, String email, String password, DataStore<String> clubMembersEmails,
                       DataStore<String> clubMembersNames, DataStore<String> clubPostsTitle,
                       DataStore<String> clubPostsDescription) {
        return new Club(username, email, password, clubMembersEmails, clubMembersNames, clubPostsTitle,
                clubPostsDescription);
    }
}
