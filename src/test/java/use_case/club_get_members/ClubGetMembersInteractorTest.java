package use_case.club_get_members;

import data_access.InMemoryUserDataAccessObject;
import entity.user.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Tests for the get members use case.
 */
public class ClubGetMembersInteractorTest {
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

        // Add 10 test members to the club.
        ArrayList<Student> verificationList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String index = String.valueOf(i);
            Student student = studentFactory.create("member" + index, "member" + index + "@mail.com", "password" + index);
            verificationList.add(student);
            testClub.addClubMember(student);
        }

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the input data for get members use case.
        ClubGetMembersInputData inputData = new ClubGetMembersInputData(clubEmail);

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubGetMembersOutputBoundary successPresenter = new ClubGetMembersOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubGetMembersOutputData outputData) {
                // Get the members Names and emails from the verification list.
                ArrayList<String> membersNames = new ArrayList<>();
                ArrayList<String> membersEmails = new ArrayList<>();

                for (Student student : verificationList) {
                    membersNames.add(student.getUsername());
                    membersEmails.add(student.getEmail());
                }

                // Check if both lists have the same contents
                assertEquals(membersNames, outputData.getMembersName());
                assertEquals(membersEmails, outputData.getMembersEmail());

                // Check if the index of both lists match with the right student.
                int index = 0;
                for (Student student : verificationList) {
                    assertEquals(student.getEmail(), membersEmails.get(index));
                    assertEquals(student.getUsername(), membersNames.get(index));
                    index++;
                }
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        // Execute the use case that need to be tested.
        ClubGetMembersInputBoundary interactor = new ClubGetMembersInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void failureTest() {
        // Initialise the club factory and StudentFactory.
        ClubFactory clubFactory = new ClubUserFactory();
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Add 10 test members to the club.
        ArrayList<Student> verificationList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String index = String.valueOf(i);
            Student student = studentFactory.create("member" + index, "member" + index + "@mail.com", "password" + index);
            userRepository.saveStudent(student);
            verificationList.add(student);
            testClub.addClubMember(student);
        }

        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the input data for get members use case with account that does not exist.
        String wrongEmail = "bogus@gmail.com";
        ClubGetMembersInputData inputData = new ClubGetMembersInputData(wrongEmail);

        // Create the failPresenter that tests whether the test case is as we expect.
        ClubGetMembersOutputBoundary failurePresenter = new ClubGetMembersOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubGetMembersOutputData outputData) {
                fail("Did not catch the error.");
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals(wrongEmail + ": Account does not exist.", errorMessage);
            }
        };

        // Execute the use case that need to be tested.
        ClubGetMembersInputBoundary interactor = new ClubGetMembersInteractor(userRepository, failurePresenter);
        interactor.execute(inputData);
    }

    @Test
    public void successTestNoMembers() {
        // Initialise the club factory and StudentFactory.
        ClubFactory clubFactory = new ClubUserFactory();

        // Create the club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Initialise the DAO. In our case, we will the in memory DAO for tests.
        InMemoryUserDataAccessObject userRepository = new InMemoryUserDataAccessObject();

        // Save the club to the in memory DAO/Database
        userRepository.saveClub(testClub);

        // Create the input data for get members use case
        ClubGetMembersInputData inputData = new ClubGetMembersInputData(clubEmail);

        // Create the successPresenter that tests whether the test case is as we expect.
        ClubGetMembersOutputBoundary successPresenter = new ClubGetMembersOutputBoundary() {
            @Override
            public void prepareSuccessView(ClubGetMembersOutputData outputData) {
                // Get the members Names and emails
                assertEquals(0, outputData.getMembersEmail().size());
                assertEquals(0, outputData.getMembersName().size());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        // Execute the use case that need to be tested.
        ClubGetMembersInputBoundary interactor = new ClubGetMembersInteractor(userRepository, successPresenter);
        interactor.execute(inputData);
    }
}
