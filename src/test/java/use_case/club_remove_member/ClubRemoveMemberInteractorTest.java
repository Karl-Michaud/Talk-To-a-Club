package use_case.club_remove_member;

import data_access.InMemoryUserDataAccessObject;
import entity.data_structure.DataStore;
import entity.user.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Tests for the club remove member use case.
 */
public class ClubRemoveMemberInteractorTest {
    static String clubName = "Test Club Name";
    static String clubEmail = "club@email.com";
    static String clubPassword = "TestClubPassword123";

    @Test
    public void successTest() {
        // Initialise the club factory and StudentFactory.
        ClubFactory clubFactory = new ClubUserFactory();
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Add 10 test members to the club and save the students.
        ArrayList<Student> membersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            // Create Individual Students
            String index = String.valueOf(i);
            Student student = studentFactory.create("member" + index, "member" + index + "@mail.com", "password" + index);

            // Save student.
            userRepository.saveStudent(student);
            // Add student to the memberList tracker and to the club.
            membersList.add(student);
            testClub.addClubMember(student);
        }

        userRepository.saveClub(testClub);

        // Create the input data for the remove member use case. We will remove all members.
        ArrayList<ClubRemoveMemberInputData> allInputs = new ArrayList<>();
        for (Student student : membersList) {
            ClubRemoveMemberInputData inputData = new ClubRemoveMemberInputData(student.getEmail(), clubEmail);
            allInputs.add(inputData);
        }

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubRemoveMemberOutputBoundary successPresenter = new ClubRemoveMemberOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubRemoveMemberOutputData outputData) {
                // If fails, will not check the rest.
                assertEquals(false, outputData.isUseCaseFailed());

                // Get the updated members
                Club updatedClub = userRepository.getClub(clubEmail);
                DataStore<Student> members = updatedClub.getClubMembers();

                // Verify that every student in the members list is
                int index = 0;
                while (index < members.size()) {
                    String studentEmail = members.getByIndex(index).getEmail();
                    String studentUsername = members.getByIndex(index).getUsername();

                    // We check the email first since it is unique.
                    assertNotEquals(outputData.getStudentEmail(), studentEmail);

                    // We check the username second since they are also unique.
                    assertNotEquals(outputData.getStudentUsername(),studentUsername);

                    // Increment
                    index++;
                }
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        // Execute the use case that need to be tested.
        ClubRemoveMemberInputBoundary interactor = new ClubRemoveMemberInteractor(userRepository,
                userRepository, successPresenter);
        for (ClubRemoveMemberInputData inputData : allInputs) {
            interactor.execute(inputData);
        }

        // Check that the club has no members since we in theory removed all members
        int numberOfMembers = userRepository.getClub(clubEmail).getClubMembers().size();
        assertEquals(0, numberOfMembers);
    }

    @Test
    public void successTestRemoveMostRecentMember() {
        // Initialise the club factory and StudentFactory.
        ClubFactory clubFactory = new ClubUserFactory();
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Add 1 student for the sake fo this test
        ArrayList<Student> membersList = new ArrayList<>();
        Student student = studentFactory.create("member1", "member1@mail.com", "password1");
        userRepository.saveStudent(student);
        testClub.addClubMember(student);

        // save club
        userRepository.saveClub(testClub);

        // Create the input data for the remove member use case. We will remove all members.
        ClubRemoveMemberInputData inputData = new ClubRemoveMemberInputData(student.getEmail(), clubEmail);

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubRemoveMemberOutputBoundary successPresenter = new ClubRemoveMemberOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubRemoveMemberOutputData outputData) {
                // If it fails, we do not bother checking the rest
                assertEquals(false, outputData.isUseCaseFailed());

                // Get the members of the updated club;
                Club updatedClub = userRepository.getClub(clubEmail);
                DataStore<Student> members = updatedClub.getClubMembers();

                // Check that the removed member is not in the members list of the club
                int size = members.size();
                assertEquals(0, size);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        // Execute the use case that need to be tested.
        ClubRemoveMemberInputBoundary interactor = new ClubRemoveMemberInteractor(userRepository,
                userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureTestNotMember() {
        // Initialise the club factory and StudentFactory.
        ClubFactory clubFactory = new ClubUserFactory();
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Add 10 test members to the club and save the students.
        ArrayList<Student> membersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String index = String.valueOf(i);
            Student student = studentFactory.create("member" + index, "member" + index + "@mail.com", "password" + index);
            userRepository.saveStudent(student);
            membersList.add(student);
            testClub.addClubMember(student);
        }

        // Create the student which is not a member of the club.
        String wrongEmail = "wrong@email.com";
        Student notInCubStudent = studentFactory.create("test", "test@mail.com", "Test");
        userRepository.saveStudent(notInCubStudent);

        // Save Club
        userRepository.saveClub(testClub);

        // Create the input data for this failed use case
        ClubRemoveMemberInputData inputData = new ClubRemoveMemberInputData(notInCubStudent.getEmail(), clubEmail);

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubRemoveMemberOutputBoundary failurePresenter = new ClubRemoveMemberOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubRemoveMemberOutputData data) {
                fail("Error not caught, user is not a member of the club.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(notInCubStudent.getEmail() + ": Account not in club.", errorMessage);
            }
        };

        // Execute the use case that need to be tested.
        ClubRemoveMemberInputBoundary interactor = new ClubRemoveMemberInteractor(userRepository,
                userRepository, failurePresenter);
        interactor.execute(inputData);

        int numberOfMembers = userRepository.getClub(clubEmail).getClubMembers().size();
        assertEquals(10, numberOfMembers);
    }

    @Test
    public void failureTestStudentDNE() {
        // Initialise the club factory and StudentFactory.
        ClubFactory clubFactory = new ClubUserFactory();
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Add 10 test members to the club and save the students.
        ArrayList<Student> membersList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String index = String.valueOf(i);
            Student student = studentFactory.create("member" + index, "member" + index + "@mail.com", "password" + index);
            userRepository.saveStudent(student);
            membersList.add(student);
            testClub.addClubMember(student);
        }

        // Save Club
        userRepository.saveClub(testClub);

        // Create the input data with an email that does not correspond to any student.
        String wrongEmail = "wrong@email.com";
        ClubRemoveMemberInputData inputData = new ClubRemoveMemberInputData(wrongEmail, clubEmail);

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubRemoveMemberOutputBoundary failurePresenter = new ClubRemoveMemberOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubRemoveMemberOutputData data) {
                fail("Error not caught, user does not exist.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(wrongEmail + ": Account does not exist.", errorMessage);
            }
        };

        // Execute the use case that need to be tested.
        ClubRemoveMemberInputBoundary interactor = new ClubRemoveMemberInteractor(userRepository,
                userRepository, failurePresenter);
        interactor.execute(inputData);

        int numberOfMembers = userRepository.getClub(clubEmail).getClubMembers().size();
        assertEquals(10, numberOfMembers);
    }

    @Test
    public void failureTestNoMembers() {
        // Initialise the club factory and StudentFactory.
        ClubFactory clubFactory = new ClubUserFactory();
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Create the student which is not a member of the club.
        String wrongEmail = "wrong@email.com";
        Student notInCubStudent = studentFactory.create("test", "test@mail.com", "Test");
        userRepository.saveStudent(notInCubStudent);

        // Save Club
        userRepository.saveClub(testClub);

        // Create the input data for this failed use case
        ClubRemoveMemberInputData inputData = new ClubRemoveMemberInputData(notInCubStudent.getEmail(), clubEmail);

        // Create the failurePresenter that tests whether the test case is as we expect.
        ClubRemoveMemberOutputBoundary failurePresenter = new ClubRemoveMemberOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubRemoveMemberOutputData data) {
                fail("Error not caught, user is not a member of the club.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(notInCubStudent.getEmail() + ": Account not in club.", errorMessage);
            }
        };

        // Execute the use case that need to be tested.
        ClubRemoveMemberInputBoundary interactor = new ClubRemoveMemberInteractor(userRepository,
                userRepository, failurePresenter);
        interactor.execute(inputData);

        int numberOfMembers = userRepository.getClub(clubEmail).getClubMembers().size();
        assertEquals(0, numberOfMembers);

    }
}
