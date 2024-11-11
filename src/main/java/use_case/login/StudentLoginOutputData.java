package use_case.login;

import entity.post.Post;

import java.util.Map;

public class StudentLoginOutputData {
    public final String username;
    public final String email;
    private final Map<Integer, Post> posts;
    private final boolean useCaseFailed;

    public StudentLoginOutputData(String username, String email, Map<Integer, Post> posts, boolean useCaseFailed) {
        this.username = username;
        this.email = email;
        this.posts = posts;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }
    public String getEmail() {
        return email;
    }
    public Map<Integer, Post> getPosts() {
        return posts;
    }
    public boolean useCaseFailed() {
        return useCaseFailed;
    }


}
