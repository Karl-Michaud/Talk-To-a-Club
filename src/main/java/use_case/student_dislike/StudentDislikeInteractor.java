package use_case.student_dislike;

import java.util.ArrayList;
import java.util.Map;

import entity.post.Post;
import entity.user.Club;
import entity.user.Student;

/**
 * Interactor for the dislike usecase.
 */
public class StudentDislikeInteractor implements StudentDislikeInputBoundary {
    private final StudentDislikeClubDataAccessInterface clubDataAccess;
    private final StudentDislikeStudentDataAccessInterface studentDataAccess;
    private final StudentDislikeOutputBoundary studentDislikePresenter;

    public StudentDislikeInteractor(StudentDislikeClubDataAccessInterface clubDataAccess,
                                    StudentDislikeStudentDataAccessInterface studentDataAccess,
                                    StudentDislikeOutputBoundary presenter) {
        this.clubDataAccess = clubDataAccess;
        this.studentDataAccess = studentDataAccess;
        this.studentDislikePresenter = presenter;
    }

    @Override
    public void execute(StudentDislikeInputData studentDislikeInputData) {
        // Input data
        final String studentEmail = studentDislikeInputData.getStudentEmail();
        final String clubEmail = studentDislikeInputData.getClubEmail();
        final Map<String, Object> postData = studentDislikeInputData.getPost();

        // Get info from db
        final Student currStudent = studentDataAccess.getStudent(studentEmail);
        final Club currClub = clubDataAccess.getClub(clubEmail);

        // Get the posts made by a club
        final ArrayList<Post> clubPosts = clubDataAccess.getPosts(currClub);

        Post postObject = null;
        // Find the corresponding Post object for the data, using the date at which it was posted.
        for (final Post post: clubPosts) {
            if (post.dateOfPosting().equals(postData.get("date"))
                    && post.timeOfPosting().equals(postData.get("time"))) {
                postObject = post;
                break;
            }
        }
        if (postObject == null) {
            studentDislikePresenter.prepareErrorView("The post doesn't exist");
        }
        else {
            if (Boolean.TRUE.equals(postObject.getDislikes().contains(currStudent.getEmail()))) {
                postObject.removeDislike(currStudent);
                clubDataAccess.savePost(postObject, currClub);
            }
            else {
                postObject.addDislike(currStudent);
                clubDataAccess.savePost(postObject, currClub);
            }
            postData.put("disliked", !(Boolean) postData.get("disliked"));

            final StudentDislikeOutputData studentDislikeOutputData = new StudentDislikeOutputData(postData,
                    currClub.getUsername());
            studentDislikePresenter.prepareSuccessView(studentDislikeOutputData);
        }
    }
}
