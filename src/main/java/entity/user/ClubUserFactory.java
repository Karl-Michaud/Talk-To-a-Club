package entity.user;

import java.util.HashMap;
import java.util.Map;

import entity.post.Post;

/**
 * Factory for creating Club user objets.
 */
public class ClubUserFactory implements ClubFactory {
    @Override
    public User create(String name, String email, String password) {
        final Map<Integer, Student> clubMembers = new HashMap<>();
        final Map<Integer, Post> clubPosts = new HashMap<>();
        return new Club(name, email, password, clubMembers, clubPosts);
    }

    /**
     * Create a new Club user.
     * @param username the username of the new club
     * @param email the email of the new club
     * @param password the password of the new club
     * @param clubMembers the members of the new club
     * @param clubPosts the posts of the new club
     * @return the new club user
     */
    public User create(String username, String email, String password, Map<Integer, Student> clubMembers,
                Map<Integer, Post> clubPosts) {
        return new Club(username, email, password, clubMembers, clubPosts);
    }
}
