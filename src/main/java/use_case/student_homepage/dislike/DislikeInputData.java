package use_case.student_homepage.dislike;

import java.util.Map;

/**
 * Input data dislike usecase.
 */
public class DislikeInputData {
    private final String studentEmail;
    private final String clubEmail;
    private final Map<String, Object> postData;

    public DislikeInputData(String studentEmail, Map<String, Object> postData) {
        this.studentEmail = studentEmail;
        this.postData = postData;
        this.clubEmail = postData.get("clubEmail").toString();
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public String getClubName() {
        return clubEmail;
    }

    public Map<String, Object> getPost() {
        return postData;
    }
}
