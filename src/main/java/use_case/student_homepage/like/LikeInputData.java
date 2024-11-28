package use_case.student_homepage.like;

import java.util.Map;

/**
 * Input data like usecase.
 */
public class LikeInputData {
    private final String studentEmail;
    private final String clubEmail;
    private final Map<String, Object> postData;

    public LikeInputData(String studentEmail, Map<String, Object> postData) {
        this.studentEmail = studentEmail;
        this.postData = postData;
        this.clubEmail = postData.get("clubEmail").toString();
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getClubEmail() {
        return clubEmail;
    }

    public Map<String, Object> getPost() {
        return postData;
    }
}
