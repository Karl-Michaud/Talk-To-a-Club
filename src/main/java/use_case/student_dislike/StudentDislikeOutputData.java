package use_case.student_dislike;

import java.util.Map;

/**
 * The output data for the dislike usecase.
 */
public class StudentDislikeOutputData {
    private final Map<String, Object> postData;
    private final String clubName;

    public StudentDislikeOutputData(Map<String, Object> postData, String clubName) {
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
