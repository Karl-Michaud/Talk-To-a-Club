package entity.user;

import java.util.Map;

import entity.post.Post;

/**
 * Factory for creating Clubs.
 */
public interface ClubFactory extends UserFactory {
    @Override
    User create(String name, String email, String password);

    /**
     * Create a new Club user.
     * @param username the username of the new club
     * @param email the email of the new club
     * @param password the password of the new club
     * @param clubMembers the members of the new club
     * @param clubPosts the posts of the new club
     * @return the new club user
     */
    User create(String username, String email, String password, Map<Integer, Student> clubMembers,
                Map<Integer, Post> clubPosts);
}
