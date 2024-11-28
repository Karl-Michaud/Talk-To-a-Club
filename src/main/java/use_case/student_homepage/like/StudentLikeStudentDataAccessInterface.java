package use_case.student_homepage.like;

import entity.user.Student;

/**
 * The DAO interface to get data related to students for the like usecase.
 */
public interface StudentLikeStudentDataAccessInterface {

    /**
     * Gets the student corresponding to the email.
     * @param studentEmail the email of the student liking or unliking a post.
     * @return the corresponding Student object to the email.
     */
    Student getStudent(String studentEmail);
}
