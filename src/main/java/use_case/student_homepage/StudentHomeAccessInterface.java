package use_case.student_homepage;

import entity.user.Student;

/**
 * Interface for the student home usecase.
 */
public interface StudentHomeAccessInterface {

    /**
     * Returns the student associated with the given email.
     * @param email of the student we are looking for.
     * @return the student with the given email.
     */
    Student getStudent(String email);
}
