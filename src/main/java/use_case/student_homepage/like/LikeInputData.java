package use_case.student_homepage.like;

import entity.post.Post;

/**
 * Input data like usecase.
 */
public class LikeInputData {
    private final String studentEmail;
    private final String clubEmail;
    private final Post post;

    public LikeInputData(String studentEmail, String clubEmail, Post post) {
        this.studentEmail = studentEmail;
        this.clubEmail = clubEmail;
        this.post = post;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getClubName() {
        return clubEmail;
    }

    public Post getPost() {
        return post;
    }
}
