package use_case.student_dislike;

import entity.user.Student;

/**
 * The DAO interface to get data related to students for the dislike usecase.
 */
public interface StudentDislikeStudentDataAccessInterface {
    /**
     * Gets the student corresponding to the email.
     * @param studentEmail the email of the student liking or unliking a post.
     * @return the corresponding Student object to the email.
     */
    Student getStudent(String studentEmail);
}
