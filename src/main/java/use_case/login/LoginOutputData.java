package use_case.login;

import entity.post.Post;
import entity.user.Club;

import java.util.Map;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {
    private final String username;
    private final String email;
    private final Map<Integer, Post> posts;
    private final Map<Integer, Club> clubs;

    private boolean isClub;
    private final boolean useCaseFailed;

    /**
     * Constructor for creating login output data for Student Users.
     * @param username the username of the Student
     * @param clubs the clubs that the Student is part of
     * @param useCaseFailed they unsuccessfully logged in (or successfully logged in - true)
     */
    public LoginOutputData(String username, Map<Integer, Club> clubs, boolean useCaseFailed) {
        this.username = username;
        this.clubs = clubs;
        this.email = null;
        this.posts = null;
        this.isClub = false;
        this.useCaseFailed = useCaseFailed;
    }

    /**
     * Constructor for creating login output data for the Club Users.
     * @param username the username of the club
     * @param email the email of the club
     * @param posts the posts of the club
     * @param useCaseFailed they unsuccessfully logged in (or successfully logged in - true)
     */
    public LoginOutputData(String username, String email, Map<Integer, Post> posts, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.posts = posts;
        this.clubs = null;
        this.isClub = true;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public boolean isClub() {
        return isClub;
    }
}
