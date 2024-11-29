package entity.user;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.AnnouncementFactory;
import entity.post.Post;
import entity.post.PostFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

/**
 * Test for the student entity.
 */
public class StudentTest {
    static String studentUsername = "student";
    static String studentEmail = "student@email.com";
    static String studentPassword = "student12345";

    @Test
    public void testStudentCreation() {
        // Initialize the student factory
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the student
        Student testStudent = studentFactory.create(studentUsername, studentEmail, studentPassword);

        // Verify that the instance variables hold true
        assertEquals(studentUsername, testStudent.getUsername());
        assertEquals(studentEmail, testStudent.getEmail());
        assertEquals(studentPassword, testStudent.getPassword());

        // Create new student
        ClubFactory clubFactory = new ClubUserFactory();
        Club testClub = clubFactory.create("test", "test@gmail.com", "test1234");
        DataStore<Club> members = new DataStoreArrays<>();
        members.add(testClub);
        Student newStudent = studentFactory.create(studentUsername, studentEmail, studentPassword, members);

        // Test
        assertEquals(studentUsername, newStudent.getUsername());
        assertEquals(studentEmail, newStudent.getEmail());
        assertEquals(studentPassword, newStudent.getPassword());
        int sizeJoinedClubs = newStudent.getJoinedClubs().size();
        assertEquals(1, sizeJoinedClubs);
    }

    @Test
    public void testStudentJoinClub() {
        // Initialize the student factory
        StudentFactory studentFactory = new StudentUserFactory();

        // Create the student
        Student testStudent = studentFactory.create(studentUsername, studentEmail, studentPassword);

        // Create 10 clubs for the student to join
        ClubFactory clubFactory = new ClubUserFactory();
        Club clubToRemove = null;
        for (int i = 0; i < 10; i++) {
            Club club = clubFactory.create("club" + i,
                    "club" + i + "@gmail.com", "password" + i);
            testStudent.joinClub(club);
            clubToRemove = club;
        }

        // Check that there are 10 clubs joined
        int sizeClubsJoined = testStudent.getJoinedClubs().size();
        assertEquals(10, sizeClubsJoined);

        // Leave club
        testStudent.leaveClub(clubToRemove);

        // Check new size
        int sizeClubsLeave = testStudent.getJoinedClubs().size();
        assertEquals(9, sizeClubsLeave);

        // Check the club is no longer in clubs joined
        DataStore<Club> joinedClubs = testStudent.getJoinedClubs();
        int index = 0;
        while (index < joinedClubs.size()) {
            // Check that the left club is no longer in the joined clubs by checking the unique username and password
            Club clubAtIndex = joinedClubs.getByIndex(index);
            assertNotEquals(clubToRemove.getEmail(), clubAtIndex.getEmail());
            assertNotEquals(clubToRemove.getUsername(), clubAtIndex.getUsername());
            index++;
        }
    }

    @Test
    public void testStudentClubManipulation() {
        String clubName = "Test Club Name";
        String clubEmail = "club@email.com";
        String clubPassword = "TestClubPassword123";
        // Initialize the club factory
        ClubFactory clubFactory = new ClubUserFactory();

        // Create a Club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Verify that the instance variables are correct
        assertEquals(clubName, testClub.getUsername());
        assertEquals(clubEmail, testClub.getEmail());
        assertEquals(clubPassword, testClub.getPassword());
        assertEquals("", testClub.getClubDescription());

        // Verify that the setter method work
        testClub.setClubDescription("Test Club Description");
        assertEquals("Test Club Description", testClub.getClubDescription());

        // Create other club
        DataStore<Student> members = new DataStoreArrays<>();
        DataStore<Post> announcements = new DataStoreArrays<>();

        // Add
        StudentFactory studentFactory = new StudentUserFactory();
        members.add(studentFactory.create("test", "test@gmail.com", "test1234"));
        members.add(studentFactory.create("test2", "test@gmail.com2", "test12342", new DataStoreArrays<>()));
        PostFactory postFactory = new AnnouncementFactory();
        announcements.add(postFactory.create("testead", "testead"));
        Club testClub2 = clubFactory.create(clubName, clubEmail, clubPassword, members, announcements);

        // Verify
        assertEquals(clubName, testClub2.getUsername());
        assertEquals(clubEmail, testClub2.getEmail());
        assertEquals(clubPassword, testClub2.getPassword());
        assertEquals("", testClub2.getClubDescription());
        int sizeMember = testClub2.getClubMembers().size();
        assertEquals(2, sizeMember);
        int sizePost = testClub2.getClubPosts().size();
        assertEquals(1, sizePost);
    }

    @Test
    public void testStudentPostManipulation() {
        String clubName = "Test Club Name";
        String clubEmail = "club@email.com";
        String clubPassword = "TestClubPassword123";

        // Initialize the club factory
        ClubFactory clubFactory = new ClubUserFactory();

        // Create a Club
        Club testClub = clubFactory.create(clubName, clubEmail, clubPassword);

        // Test the members
        StudentFactory studentFactory = new StudentUserFactory();
        Student studentToRemove = null;
        for (int i = 0; i < 10; i++) {
            Student student = studentFactory.create("member" + i, "member" + i +"@gmail.com",
                    "password" + i);
            testClub.addClubMember(student);
            studentToRemove = student;
        }
        // Check that there are 10 members
        int sizeMembers = testClub.getClubMembers().size();
        assertEquals(10, sizeMembers);

        // Remove Student
        testClub.removeClubMember(studentToRemove);

        // Check new size
        int sizeMembersAfterRemove = testClub.getClubMembers().size();
        assertEquals(9, sizeMembersAfterRemove);

        // Check if member is out
        DataStore<Student> members = testClub.getClubMembers();
        int index = 0;
        while (index < members.size()) {
            Student studentAtIndex = members.getByIndex(index);
            // Check for name and password since they are in theory unique.
            assertNotEquals(studentToRemove.getEmail(), studentAtIndex.getEmail());
            assertNotEquals(studentToRemove.getUsername(), studentAtIndex.getUsername());
            index++;
        }

        // Create a Club
        Club testClub2 = clubFactory.create(clubName, clubEmail, clubPassword);

        // Create 10 club posts
        PostFactory postFactory = new AnnouncementFactory();
        Post postToRemove = null;
        for (int i = 0; i < 10; i++) {
            Post post = postFactory.create("post" + i, "description" + i);
            testClub2.addClubPost(post);
            postToRemove = post;
        }

        // Check that there are 10 posts
        int sizePosts = testClub2.getClubPosts().size();
        assertEquals(10, sizePosts);

        // Remove post
        testClub2.removeClubPost(postToRemove);

        // Check new size
        int sizePostsAfterRemove = testClub2.getClubPosts().size();
        assertEquals(9, sizePostsAfterRemove);

        // Check if post is out
        DataStore<Post> posts = testClub2.getClubPosts();
        int index2 = 0;
        while (index2 < posts.size()) {
            Post postAtIndex = posts.getByIndex(index2);

            // Verify that no posts has the same exact title and description
            assertTrue(!postToRemove.getTitle().equals(postAtIndex.getTitle()) &&
                    !postToRemove.getContent().equals(postAtIndex.getContent()));
            index2++;
        }
    }
}
