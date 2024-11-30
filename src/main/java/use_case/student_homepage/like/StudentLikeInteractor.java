package use_case.student_homepage.like;

import java.util.ArrayList;
import java.util.Map;

import entity.data_structure.DataStoreArrays;
import entity.post.Post;
import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for the like usecase.
 */
public class StudentLikeInteractor implements StudentLikeInputBoundary {
    private final StudentLikeStudentDataAccessInterface studentDataAccess;
    private final StudentLikeClubDataAccessInterface clubDataAccess;
    private final StudentLikeOutputBoundary studentLikePresenter;

    public StudentLikeInteractor(StudentLikeStudentDataAccessInterface studentDataAccess,
                                 StudentLikeClubDataAccessInterface clubDataAccess,
                                 StudentLikeOutputBoundary studentLikePresenter) {
        this.studentDataAccess = studentDataAccess;
        this.clubDataAccess = clubDataAccess;
        this.studentLikePresenter = studentLikePresenter;
    }

    @Override
    public void execute(StudentLikeInputData studentLikeInputData) {
        // Input data
        final String studentEmail = studentLikeInputData.getStudentEmail();
        final String clubEmail = studentLikeInputData.getClubEmail();
        final Map<String, Object> postData = studentLikeInputData.getPost();

        // DB info
        final Student currStudent = studentDataAccess.getStudent(studentEmail);
        final Club currClub = clubDataAccess.getClub(clubEmail);

        // Get the posts made by a club
        final ArrayList<Post> clubPosts = clubDataAccess.getPosts(currClub);

        // Find the corresponding Post object for the data, using the date at which it was posted.
        Post postObject = null;
        for (final Post post: clubPosts) {
            if (post.dateOfPosting() == postData.get("date") && post.timeOfPosting() == postData.get("time")) {
                postObject = post;
                break;
            }
        }
        if (postObject == null) {
            studentLikePresenter.prepareErrorView("post does not exist");
        }
        else {
            if (Boolean.TRUE.equals(postObject.getLikes().contains(currStudent.getEmail()))) {
                // Not the condition uses .getEmail due to the implementation of Post and the like instance variable.
                postObject.removeLike(currStudent);
                clubDataAccess.savePost(postObject, currClub);
            }
            else {
                postObject.addLike(currStudent);
                clubDataAccess.savePost(postObject, currClub);
            }
            postData.put("liked", !(Boolean) postData.get("liked"));

            final StudentLikeOutputData studentLikeOutputData = new StudentLikeOutputData(postData,
                    currClub.getUsername());
            studentLikePresenter.prepareSuccessView(studentLikeOutputData);
        }
    }
}
