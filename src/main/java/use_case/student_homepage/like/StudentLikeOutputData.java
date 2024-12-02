package use_case.student_homepage.like;

import java.util.Map;

/**
 * The output data for the like usecase.
 */
public class StudentLikeOutputData {
    private final Map<String, Object> postData;
    private final String clubName;

    public StudentLikeOutputData(Map<String, Object> postData, String clubName) {
        this.postData = postData;
        this.clubName = clubName;
    }

    public Map<String, Object> getPostData() {
        return postData;
    }

    public String getClubName() {
        return clubName;
    }
}
