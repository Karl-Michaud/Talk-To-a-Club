package entity.user;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.AnnouncementFactory;
import entity.post.Post;
import entity.post.PostFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Test for the student entity.
 */
public class StudentTest {
    static String studentUsername = "student";
    static String studentEmail = "student@email.com";
    static String studentPassword = "student12345";

    @Test
    public void testStudent() {
        // Initialize the student factory
        StudentFactory studentFactory = new StudentUserFactory();

        // Create a test student
        Student student = studentFactory.create(studentUsername, studentEmail, studentPassword);

        // Check data
        assertNotNull(student);
        assertEquals(student.getUsername(), studentUsername);
        assertEquals(student.getEmail(), studentEmail);
        assertEquals(student.getPassword(), studentPassword);

        // Check that the clubs joined is empty since the student profile just got created
        int sizeClubNames = student.getJoinedClubsNames().size();
        int sizeClubEmails = student.getJoinedClubsEmails().size();

        assertEquals(sizeClubNames, 0);
        assertEquals(sizeClubEmails, 0);
    }

    @Test
    public void testClubManipulation() {
        // Initialize the student factory
        StudentFactory studentFactory = new StudentUserFactory();

        // Create a test student
        Student student = studentFactory.create(studentUsername, studentEmail, studentPassword);

        // Create 10 clubs to join
        ClubFactory clubFactory = new ClubUserFactory();
        ArrayList<Club> tracker = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Club club = clubFactory.create("club" + String.valueOf(i), "club@" + String.valueOf(i),
                    "club" + String.valueOf(i));
            student.joinClub(club);
            tracker.add(club);
        }

        int sizeClubNames = student.getJoinedClubsNames().size();
        int sizeClubEmails = student.getJoinedClubsEmails().size();

        assertEquals(sizeClubNames, 10);
        assertEquals(sizeClubEmails, 10);

        // Check that the name match and leave the club
        for (int i = 0; i < 10; i++) {
            assertEquals(tracker.get(i).getEmail(), student.getJoinedClubsEmails().getByIndex(i));
            assertEquals(tracker.get(i).getUsername(), student.getJoinedClubsNames().getByIndex(i));
            student.leaveClub(tracker.get(i));
        }

        int sizeClubNamesAfter = student.getJoinedClubsNames().size();
        int sizeClubEmailsAfter = student.getJoinedClubsEmails().size();

        assertEquals(sizeClubNamesAfter, 0);
        assertEquals(sizeClubEmailsAfter, 0);

    }
}
