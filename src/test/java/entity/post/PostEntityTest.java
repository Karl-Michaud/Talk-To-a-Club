package entity.post;

import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.user.Student;
import entity.user.StudentUserFactory;
import entity.user.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class PostEntityTest {

    @Test
    void testPostCreation() {
        PostFactory factory = new AnnouncementFactory();
        Post post = factory.create("Test Title", "This is the content of the test post.");

        assertEquals("Test Title", post.getTitle(), "Post title should match the input.");
        assertEquals("This is the content of the test post.", post.getContent(), "Post content should match the input.");
        assertNotNull(post.dateOfPosting(), "Post date should not be null.");
        assertNotNull(post.timeOfPosting(), "Post time should not be null.");
    }

    @Test
    void testSetDateOfPosting() {
        PostFactory factory = new AnnouncementFactory();
        Announcement post = (Announcement) factory.create("Title", "Content");

        LocalDate newDate = LocalDate.of(2023, 12, 1);
        post.setDateOfPosting(newDate);

        assertEquals(newDate, post.dateOfPosting(), "Post date should be updated correctly.");
    }

    @Test
    void testSetTimeOfPosting() {
        PostFactory factory = new AnnouncementFactory();
        Announcement post = (Announcement) factory.create("Title", "Content");

        LocalTime newTime = LocalTime.of(14, 30);
        post.setTimeOfPosting(newTime);

        assertEquals(newTime, post.timeOfPosting(), "Post time should be updated correctly.");
    }

    @Test
    void testLikePost() {
        PostFactory factory = new AnnouncementFactory();
        Post post = factory.create("Sample Title", "Sample Content");

        DataStore<String> joinedEmail = new DataStoreArrays<>();
        DataStore<String> joinedName = new DataStoreArrays<>();
        User user = new Student("John Doe", "johndoe@example.com", "12345678hello", joinedEmail, joinedName);

        post.addLike(user);
        assertEquals(1, post.numberOfLikes());
        assertTrue(post.getLikes().contains(user.getEmail()));

        post.removeLike(user);
        assertEquals(0, post.numberOfLikes());
        assertFalse(post.getLikes().contains(user.getEmail()));
    }

    @Test
    void testDislikePost() {
        PostFactory factory = new AnnouncementFactory();
        Post post = factory.create("Sample Title", "Sample Content");
        StudentUserFactory studentFactory = new StudentUserFactory();
        Student user = studentFactory.create("John Doe", "johndoe@example.com", "12345678hello");

        post.addDislike(user);
        assertEquals(1, post.numberOfDislikes());
        assertTrue(post.getDislikes().contains(user.getEmail()));

        post.removeDislike(user);
        assertEquals(0, post.numberOfDislikes());
        assertFalse(post.getDislikes().contains(user.getEmail()));
    }

    @Test
    void testMultipleLikesAndDislikes() {
        PostFactory factory = new AnnouncementFactory();
        Post post = factory.create("Title", "Content");

        StudentUserFactory studentFactory = new StudentUserFactory();
        User user1 = studentFactory.create("Alice", "alice@example.com", "12345678hello");
        User user2 = studentFactory.create("Bob", "bob@example.com", "12345678hello");

        post.addLike(user1);
        post.addLike(user2);
        assertEquals(2, post.numberOfLikes());

        post.addDislike(user1);
        assertEquals(1, post.numberOfDislikes());

        post.removeLike(user2);
        assertEquals(1, post.numberOfLikes());
    }

    @Test
    void testPostMetadata() {
        PostFactory factory = new AnnouncementFactory();
        Post post = factory.create("Metadata Title", "Metadata Content");

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        assertEquals(today, post.dateOfPosting());
        assertNotNull(post.timeOfPosting());
    }

    @Test
    void testPostFactory() {
        PostFactory factory = new AnnouncementFactory();
        Post post = factory.create("Factory Title", "Factory Content");

        assertEquals("Factory Title", post.getTitle());
        assertEquals("Factory Content", post.getContent());
        assertInstanceOf(Announcement.class, post, "Factory should create an instance of Announcement.");
    }
    @Test
    void testPostFactoryUsersLikedDisliked() {
        AnnouncementFactory factory = new AnnouncementFactory();
        DataStoreArrays userLiked = new DataStoreArrays<>();
        DataStoreArrays userDisliked = new DataStoreArrays<>();

        Post post = factory.create("Factory Title", "Factory Content", userLiked, userDisliked);
        assertEquals("Factory Title", post.getTitle());
        assertEquals("Factory Content", post.getContent());
        assertInstanceOf(Announcement.class, post, "Factory should create an instance of Announcement.");
    }
}
