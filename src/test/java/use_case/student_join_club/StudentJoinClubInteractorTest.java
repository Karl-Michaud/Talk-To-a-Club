package use_case.student_join_club;

import data_access.InMemoryUserDataStudentAccessObject;
import entity.user.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentJoinClubInteractorTest {
    @Test
    void successTest() {
        // Set up in-memory repositories
        InMemoryUserDataStudentAccessObject userRepository = new InMemoryUserDataStudentAccessObject();

        // Create a club
        ClubFactory clubFactory = new ClubUserFactory();

        Club club = clubFactory.create("Photography Club", "photo@university.com", "password");
        club.setClubDescription("For photography enthusiasts.");

        // Create a student not in the club
        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Alice", "alice@university.com", "password");
        userRepository.saveStudent(student);
        userRepository.saveClub(club);

        // Input data
        StudentJoinClubInputData inputData = new StudentJoinClubInputData("alice@university.com", "photo@university.com");

        // Output boundary for success verification
        StudentJoinClubOutputBoundary successPresenter = new StudentJoinClubOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentJoinClubOutputData data) {
                assertEquals("Alice", data.getUsername());
                assertFalse(data.isUseCaseFailed());
                assertTrue(club.getClubMembersEmails().contains(student.getEmail()));
                assertTrue(student.getJoinedClubsEmails().contains(club.getEmail()));
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Unexpected fail call");
            }
        };

        // Interactor execution
        StudentJoinClubInteractor interactor = new StudentJoinClubInteractor(userRepository, userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    void clubDoesNotExistTest() {
        // Set up in-memory repositories
        InMemoryUserDataStudentAccessObject userRepository = new InMemoryUserDataStudentAccessObject();

        // Create a student
        StudentFactory studentFactory = new StudentUserFactory();
        Student student = studentFactory.create("Alice", "alice@university.com", "password");
        userRepository.saveStudent(student);

        // Input data with a non-existent club
        StudentJoinClubInputData inputData = new StudentJoinClubInputData("alice@university.com", "nonexistent@university.com");

        // Output boundary for failure verification
        StudentJoinClubOutputBoundary failPresenter = new StudentJoinClubOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentJoinClubOutputData data) {
                fail("Unexpected success call");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("alice@university.com: Club does not exist.", errorMessage);
            }
        };

        // Interactor execution
        StudentJoinClubInteractor interactor = new StudentJoinClubInteractor(userRepository, userRepository, failPresenter);
        interactor.execute(inputData);
    }

    @Test
    void studentAlreadyInClubTest() {
        // Set up in-memory repositories
        InMemoryUserDataStudentAccessObject userRepository = new InMemoryUserDataStudentAccessObject();

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
        StudentJoinClubInputData inputData = new StudentJoinClubInputData("alice@university.com", "photo@university.com");

        // Output boundary for failure verification
        StudentJoinClubOutputBoundary failPresenter = new StudentJoinClubOutputBoundary() {
            @Override
            public void prepareSuccessView(StudentJoinClubOutputData data) {
                fail("Unexpected success call");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("alice@university.com: Account in club already.", errorMessage);
            }
        };

        // Interactor execution
        StudentJoinClubInteractor interactor = new StudentJoinClubInteractor(userRepository, userRepository, failPresenter);
        interactor.execute(inputData);
    }
}
