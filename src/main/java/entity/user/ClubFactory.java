package entity.user;

import entity.data_structure.DataStore;

/**
 * Factory for creating Clubs.
 */
public interface ClubFactory extends UserFactory {
    @Override
    Club create(String name, String email, String password);

    /**
     * Create a new Club user.
     * @param username the username of the new club
     * @param email the email of the new club
     * @param password the password of the new club
     * @param clubMembersEmails the members email
     * @param clubMembersNames the members name
     * @param clubPostsTitle the posts title
     * @param clubPostsDescription the posts description
     * @return the new club user
     */
    Club create(String username, String email, String password, DataStore<String> clubMembersEmails,
                DataStore<String> clubMembersNames, DataStore<String> clubPostsTitle,
                DataStore<String> clubPostsDescription);
}
