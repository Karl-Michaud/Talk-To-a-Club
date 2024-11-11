package entity.user;

import java.util.Map;

import entity.post.Post;

/**
 * This is the Club class which implements the User interface. A Club is a typer of User.
 */
public class Club implements User {
    // Club's personal information
    private String username;
    private String email;
    private String password;
    private int clubID;

    // Club's members and Posts information
    private Map<Integer, Student> clubMembers;
    private Map<Integer, Post> clubPosts;

    public Club(String username, String email, String password, Map<Integer, Student> clubMembers,
                Map<Integer, Post> clubPosts) {
        // Initialise club personal information
        this.username = username;
        this.email = email;
        this.password = password;

        // Temporarily sets the club's ID to -1. This means that the user is not in the database yet.
        this.clubID = -1;

        // Initialise club members and posts information
        this.clubMembers = clubMembers;
        this.clubPosts = clubPosts;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserID() {
        return clubID;
    }

    public void setUserID(int userID) {
        this.clubID = userID;
    }

    public Map<Integer, Student> getClubMembers() {
        return clubMembers;
    }

    public Map<Integer, Post> getClubPosts() {
        return clubPosts;
    }

    /**
     * Adds a club member to the Club.
     * @param user particular user joining the club.
     */
    public void addClubMember(Student user) {
        clubMembers.put(user.getUserID(), user);
    }

    /**
     * Adds a club post to the history of the club's posts.
     * @param post particular post to add.
     */
    public void addClubPost(Post post) {
        clubPosts.put(post.getPostID(), post);
    }

    /**
     * Removes a user from the club.
     * @param user particular user leaving the club.
     */
    public void removeClubMember(User user) {
        clubMembers.remove(user.getUserID());
    }

    /**
     * Removes a club post from the club posts' history.
     * @param post particular post to be removed
     */
    public void removeClubPost(Post post) {
        clubPosts.remove(post.getPostID());
    }

}
