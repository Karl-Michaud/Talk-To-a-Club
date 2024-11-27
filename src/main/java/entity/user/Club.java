package entity.user;

import entity.data_structure.DataStore;
import entity.post.Post;

/**
 * This is the Club class which implements the User interface. A Club is a typer of User.
 */
public class Club implements User {
    // Club's personal information
    private final String username;
    private final String email;
    private final String password;
    private String clubDescription = "";
    // Club's members and Posts information
    private final DataStore<Student> clubMembers;
    private final DataStore<Post> clubPosts;

    public Club(String username, String email, String password, DataStore<Student> clubMembers,
                DataStore<Post> clubPosts) {
        // Initialize club personal information
        this.username = username;
        this.email = email;
        this.password = password;

        // Initialize club members and posts information
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

    public String getClubDescription() {
        return clubDescription;
    }

    public DataStore<Student> getClubMembers() {
        return clubMembers;
    }

    public DataStore<Post> getClubPosts() {
        return clubPosts;
    }

    /**
     * Adds a club member to the Club.
     * @param user particular user joining the club.
     */
    public void addClubMember(Student user) {
        clubMembers.add(user);
    }

    /**
     * Adds a club post to the history of the club's posts.
     * @param post particular post to add.
     */
    public void addClubPost(Post post) {
        clubPosts.add(post);
    }

    /**
     * Removes a user from the club.
     * @param user particular user leaving the club.
     */
    public void removeClubMember(Student user) {
        clubMembers.remove(user);
    }

    /**
     * Removes a club post from the club posts' history.
     * @param post particular post to be removed
     */
    public void removeClubPost(Post post) {
        clubPosts.remove(post);
    }

    public void setClubDescription(String newDescription) {
        this.clubDescription = newDescription;
    }
}
