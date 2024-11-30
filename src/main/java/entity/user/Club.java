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
    private final DataStore<String> clubMembersEmails;
    private final DataStore<String> clubMembersNames;

    private final DataStore<String> clubPostsTitle;
    private final DataStore<String> clubPostsDescription;

    public Club(String username, String email, String password, DataStore<String> clubMembersEmails,
                DataStore<String> clubMembersNames, DataStore<String> clubPostsTitle,
                DataStore<String> clubPostsDescription) {
        // Initialize club personal information
        this.username = username;
        this.email = email;
        this.password = password;

        // Initialize club members and posts information
        this.clubMembersEmails = clubMembersEmails;
        this.clubMembersNames = clubMembersNames;
        this.clubPostsTitle = clubPostsTitle;
        this.clubPostsDescription = clubPostsDescription;
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

    public DataStore<String> getClubMembersEmails() {
        return clubMembersEmails;
    }

    public DataStore<String> getClubMembersNames() {
        return clubMembersNames;
    }

    public DataStore<String> getClubPostsTitle() {
        return clubPostsTitle;
    }

    public DataStore<String> getClubPostsDescription() {
        return clubPostsDescription;
    }

    /**
     * Adds a club member to the Club.
     * @param user particular user joining the club.
     */
    public void addClubMember(Student user) {
        clubMembersEmails.add(user.getEmail());
        clubMembersNames.add(user.getUsername());
    }

    /**
     * Adds a club post to the history of the club's posts.
     * @param post particular post to add.
     */
    public void addClubPost(Post post) {
        clubPostsTitle.add(post.getTitle());
        clubPostsDescription.add(post.getContent());
    }

    /**
     * Removes a user from the club.
     * @param user particular user leaving the club.
     */
    public void removeClubMember(Student user) {
        clubMembersNames.remove(user.getUsername());
        clubMembersEmails.remove(user.getEmail());
    }

    /**
     * Removes a club post from the club posts' history.
     * @param post particular post to be removed
     */
    public void removeClubPost(Post post) {
        clubPostsDescription.remove(post.getTitle());
        clubMembersNames.remove(post.getContent());
    }

    public void setClubDescription(String newDescription) {
        this.clubDescription = newDescription;
    }
}
