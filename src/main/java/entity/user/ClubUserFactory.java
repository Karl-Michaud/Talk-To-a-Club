package entity.user;

import java.util.ArrayList;

import entity.post.Post;

/**
 * Factory for creating Club user objets.
 */
public class ClubUserFactory implements ClubFactory {
    @Override
    public Club create(String name, String email, String password) {
        final ArrayList<Student> clubMembers = new ArrayList<>();
        final ArrayList<Post> clubPosts = new ArrayList<>();
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
    public Club create(String username, String email, String password, ArrayList<Student> clubMembers,
                ArrayList<Post> clubPosts) {
        return new Club(username, email, password, clubMembers, clubPosts);
    }
}
