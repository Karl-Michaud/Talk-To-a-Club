package use_case.student_leave_club;

import data_access.InMemoryUserDataAccessObject;
import entity.user.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentLeaveClubInteractorTest {
    @Test
    void successTest() {
        // Set up in-memory repositories
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a club
        ClubFactory clubFactory = new ClubUserFactory();
        Club club = clubFactory.create("Photography Club", "photo@university.com", "password");
        club.setClubDescription("For photography enthusiasts.");

        // Create a student already in the club
        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Alice", "alice@university.com", "password");
        student.joinClub(club);
        club.addClubMember(student);
        userRepository.saveStudent(student);
        userRepository.saveClub(club);

        // Input data
        StudentLeaveClubInputData inputData = new StudentLeaveClubInputData("alice@university.com", "photo@university.com");

        // Output boundary for success verification
        StudentLeaveClubOutputBoundary successPresenter = new StudentLeaveClubOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentLeaveClubOutputData data) {
                assertEquals("Alice", data.getUsername());
                assertFalse(data.isUseCaseFailed());
                assertFalse(club.getClubMembersEmails().contains(student.getEmail()));
                assertFalse(student.getJoinedClubsEmails().contains(club.getEmail()));
                assertEquals("photo@university.com", data.getClubEmail());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected fail call");
            }
        };

        // Interactor execution
        StudentLeaveClubInteractor interactor = new StudentLeaveClubInteractor(userRepository, userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void clubDoesNotExistTest() {
        // Set up in-memory repositories
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a student
        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Alice", "alice@university.com", "password");
        userRepository.saveStudent(student);

        // Input data with a non-existent club
        StudentLeaveClubInputData inputData = new StudentLeaveClubInputData("alice@university.com", "nonexistent@university.com");

        // Output boundary for failure verification
        StudentLeaveClubOutputBoundary failPresenter = new StudentLeaveClubOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentLeaveClubOutputData data) {
                fail("Unexpected success call");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("alice@university.com: Club does not exist.", errorMessage);
            }
        };

        // Interactor execution
        StudentLeaveClubInteractor interactor = new StudentLeaveClubInteractor(userRepository, userRepository, failPresenter);
        interactor.execute(inputData);
    }

    @Test
    void studentNotInClubTest() {
        // Set up in-memory repositories
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a club
        ClubFactory clubFactory = new ClubUserFactory();
        Club club = clubFactory.create("Photography Club", "photo@university.com", "password");
        club.setClubDescription("For photography enthusiasts.");
        userRepository.saveClub(club);

        // Create a student not in the club
        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Alice", "alice@university.com", "password");
        userRepository.saveStudent(student);

        // Input data
        StudentLeaveClubInputData inputData = new StudentLeaveClubInputData("alice@university.com", "photo@university.com");

        // Output boundary for failure verification
        StudentLeaveClubOutputBoundary failPresenter = new StudentLeaveClubOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentLeaveClubOutputData data) {
                fail("Unexpected success call");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("alice@university.com: Account not in club.", errorMessage);
            }
        };

        // Interactor execution
        StudentLeaveClubInteractor interactor = new StudentLeaveClubInteractor(userRepository, userRepository, failPresenter);
        interactor.execute(inputData);
    }
}
