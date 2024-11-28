package use_case.student_join_club;

import data_access.InMemoryUserDataAccessObject;
import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.user.Club;
import entity.user.Student;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StudentJoinClubInteractorTest {
    @Test
    void successTest() {
        // Set up in-memory repositories
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a club
        DataStore<Student> students1 = new DataStoreArrays<Student>();
        Club club = new Club("Photography Club", "photo@university.com", "password", students1, null);
        club.setClubDescription("For photography enthusiasts.");

        // Create a student not in the club
        DataStore<Club> joinedClubs = new DataStoreArrays<>();
        Student student = new Student("Alice", "alice@university.com", "password", joinedClubs);
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
                assertTrue(club.getClubMembers().contains(student));
                assertTrue(student.getJoinedClubs().contains(club));
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
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a student
        DataStore<Club> joinedClubs = new DataStoreArrays<>();
        Student student = new Student("Alice", "alice@university.com", "password", joinedClubs);
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
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create a club
        DataStore<Student> students = new DataStoreArrays<Student>();
        Club club = new Club("Photography Club", "photo@university.com", "password", students, null);
        club.setClubDescription("For photography enthusiasts.");

        // Create a student already in the club
        DataStore<Club> joinedClubs = new DataStoreArrays<>();
        Student student = new Student("Alice", "alice@university.com", "password", joinedClubs);
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
