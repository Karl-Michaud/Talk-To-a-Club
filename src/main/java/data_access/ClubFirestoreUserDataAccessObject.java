package data_access;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.Announcement;
import entity.post.AnnouncementFactory;
import entity.post.Post;
import entity.user.Club;
import entity.user.ClubUserFactory;
import use_case.club_create_post.ClubCreatePostUserDataAccessInterface;
import use_case.club_get_members.ClubGetMembersUserDataAccessInterface;
import use_case.club_get_posts.ClubGetPostsDataAccessInterface;
import use_case.club_remove_member.ClubRemoveMemberClubDataAccessInterface;
import use_case.club_update_desc.ClubUpdateDescDataAccessInterface;
import use_case.explore_clubs.ClubExploreClubsDataAccessInterface;
import use_case.login.club_login.ClubLoginDataAccessInterface;
import use_case.signup.club_signup.ClubSignupDataAccessInterface;
import use_case.student_homepage.dislike.StudentDislikeClubDataAccessInterface;
import use_case.student_homepage.like.StudentLikeClubDataAccessInterface;
import use_case.student_homepage.show_posts.StudentShowPostsClubAccessInterface;
import use_case.student_join_club.ClubStudentJoinClubDataAccessInterface;
import use_case.student_leave_club.ClubStudentLeaveClubDataAccessInterface;

/**
 * Persisting memory implementation of the DAO for storing user data.
 * This implementation uses Firebase and only persists data regarding the
 * Club entity
 */
public class ClubFirestoreUserDataAccessObject implements ClubCreatePostUserDataAccessInterface,
        ClubGetMembersUserDataAccessInterface, ClubRemoveMemberClubDataAccessInterface, ClubLoginDataAccessInterface,
        ClubSignupDataAccessInterface, ClubStudentJoinClubDataAccessInterface,
        ClubStudentLeaveClubDataAccessInterface, ClubExploreClubsDataAccessInterface, ClubGetPostsDataAccessInterface,
        ClubUpdateDescDataAccessInterface, StudentDislikeClubDataAccessInterface,
        StudentLikeClubDataAccessInterface, StudentShowPostsClubAccessInterface {
    private static final String CLUBS = "clubs";
    private static final String USERNAME = "username";
    private static final String CLUB_DESCRIPTION = "clubDescription";
    private static final String PASSWORD = "password";
    private static final String CLUB_MEMBER_EMAILS = "clubMembersEmails";
    private static final String CLUB_POSTS_TITLE = "clubPostsTitle";
    private static final String CLUB_POSTS_DESCRIPTION = "clubPostsDescription";
    private static final String ALL_POSTS = "allPosts";
    private static final String DATE_OF_POSTING = "dateOfPosting";
    private static final String TIME_OF_POSTING = "timeOfPosting";
    private static final String USER_LIKED = "userLiked";
    private static final String USER_DISLIKED = "userDisliked";
    private static final String CLUB_MEMBER_NAMES = "clubMembersNames";
    private static final String UPDATE_TIME = "Update time: ";

    private final Firestore db;

    private Logger logger;

    public ClubFirestoreUserDataAccessObject() {
        this.db = FirestoreClient.getFirestore();
        this.logger = Logger.getLogger(this.getClass().getName());
    }

    @Override
    public boolean existsByNameClub(String username) {
        final ApiFuture<QuerySnapshot> future = db.collection(CLUBS).whereEqualTo(USERNAME, username).get();
        boolean returnValue;
        try {
            returnValue = !future.get().isEmpty();
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public boolean existsByEmailClub(String email) {
        final DocumentReference docRef = db.collection(CLUBS).document(email);
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        boolean returnValue;
        try {
            final DocumentSnapshot document = future.get();
            returnValue = document.exists();
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
            returnValue = false;
        }
        return returnValue;
    }

    @Override
    public void updateClubDescription(Club club) {
        // method updates the club data including the new post.
        final DocumentReference docRef = db.collection(CLUBS).document(club.getEmail());
        final ApiFuture<WriteResult> writeResult = docRef.update(CLUB_DESCRIPTION, club.getClubDescription());
        try {
            logger.log(Level.INFO, UPDATE_TIME + writeResult.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
        }
    }

    @Override
    public void saveClub(Club user) {
        final DocumentReference docRef = db.collection(CLUBS).document(user.getEmail());
        final Map<String, Object> mapClub = new HashMap<>();
        mapClub.put(USERNAME, user.getUsername());
        mapClub.put("email", user.getEmail());
        mapClub.put(PASSWORD, user.getPassword());
        mapClub.put(CLUB_DESCRIPTION, user.getClubDescription());
        mapClub.put(CLUB_MEMBER_EMAILS, user.getClubMembersEmails().toArrayList().stream().toList());
        mapClub.put(CLUB_MEMBER_NAMES, user.getClubMembersNames().toArrayList().stream().toList());
        mapClub.put(CLUB_POSTS_TITLE, user.getClubPostsTitle().toArrayList().stream().toList());
        mapClub.put(CLUB_POSTS_DESCRIPTION, user.getClubPostsDescription().toArrayList().stream().toList());
        mapClub.put(ALL_POSTS, new ArrayList<Map<String, Object>>().stream().toList());

        final ApiFuture<WriteResult> writeResult = docRef.set(mapClub);

        try {
            logger.log(Level.INFO, UPDATE_TIME + writeResult.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
        }
    }

    @Override
    public void savePost(Post post, Club club) {
        // same implementation as saveClub
        // method overwrites the club data including the new post.
        final DocumentReference docRef = db.collection(CLUBS).document(club.getEmail());
        final ApiFuture<DocumentSnapshot> future = docRef.get();

        final ApiFuture<WriteResult> writeTitle = docRef.update(CLUB_POSTS_TITLE,
                club.getClubPostsTitle().toArrayList().stream().toList());
        final ApiFuture<WriteResult> writePostDescription = docRef.update(CLUB_POSTS_DESCRIPTION,
                club.getClubPostsDescription().toArrayList().stream().toList());

        try {
            final DocumentSnapshot document = future.get();
            final ArrayList<Map<String, Object>> clubPostValues =
                    (ArrayList<Map<String, Object>>) document.get(ALL_POSTS);

            // Check if the post exists
            Integer savedIndex = null;
            for (int i = 0; i < clubPostValues.size(); i++) {
                final Map<String, Object> clubPost = clubPostValues.get(i);
                if (clubPost.get(DATE_OF_POSTING).equals(post.dateOfPosting().toString())
                    && clubPost.get(TIME_OF_POSTING).equals(post.timeOfPosting().toString())) {
                    savedIndex = i;
                }
            }

            if (savedIndex == null) {
                final Map<String, Object> clubPost = new HashMap<>();
                clubPost.put("title", post.getTitle());
                clubPost.put("content", post.getContent());
                clubPost.put(DATE_OF_POSTING, post.dateOfPosting().toString());
                clubPost.put(TIME_OF_POSTING, post.timeOfPosting().toString());
                clubPost.put(USER_LIKED, post.getLikes().toArrayList().stream().toList());
                clubPost.put(USER_DISLIKED, post.getDislikes().toArrayList().stream().toList());

                clubPostValues.add(clubPost);

            }
            else {
                clubPostValues.get(savedIndex).put(USER_LIKED, post.getLikes().toArrayList().stream().toList());
                clubPostValues.get(savedIndex).put(USER_DISLIKED, post.getDislikes().toArrayList().stream().toList());
            }

            final ApiFuture<WriteResult> writePostListUpdate = docRef.update(ALL_POSTS,
                    clubPostValues);

            logger.log(Level.INFO, UPDATE_TIME + writePostListUpdate.get().getUpdateTime());
            logger.log(Level.INFO, UPDATE_TIME + writeTitle.get().getUpdateTime());
            logger.log(Level.INFO, UPDATE_TIME + writePostDescription.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Post> getPosts(Club club) {
        final DocumentReference docRef = db.collection(CLUBS).document(club.getEmail());
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        final ArrayList<Post> postValues = new ArrayList<>();

        try {
            final DocumentSnapshot document = future.get();
            final ArrayList<Map<String, Object>> clubPostValues =
                    (ArrayList<Map<String, Object>>) document.get(ALL_POSTS);
            for (Map<String, Object> clubPost : clubPostValues) {
                final AnnouncementFactory announcementFactory = new AnnouncementFactory();
                final String title = clubPost.get("title").toString();
                final String content = clubPost.get("content").toString();
                final ArrayList userLiked = (ArrayList) clubPost.get(USER_LIKED);
                final ArrayList userDisliked = (ArrayList) clubPost.get(USER_DISLIKED);

                final Announcement post = announcementFactory.create(title, content,
                        (DataStoreArrays) new DataStoreArrays().toDataStore(userLiked),
                        (DataStoreArrays) new DataStoreArrays().toDataStore(userDisliked));
                post.setTimeOfPosting(LocalTime.parse(clubPost.get(TIME_OF_POSTING).toString()));
                post.setDateOfPosting(LocalDate.parse(clubPost.get(DATE_OF_POSTING).toString()));

                postValues.add(post);
            }
        }
        catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }

        return postValues;
    }

    @Override
    public Club getClub(String email) {
        // get club through email
        final DocumentReference docRef = db.collection(CLUBS).document(email);
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        Club returnValue = null;
        try {
            final DocumentSnapshot document = future.get();
            if (document.exists()) {

                final String username = document.getString(USERNAME);
                final String password = document.getString(PASSWORD);
                final String clubDescription = document.getString(CLUB_DESCRIPTION);
                // Club's members and Posts information
                final ArrayList<String> memberEmails;
                memberEmails = (ArrayList<String>) document.get(CLUB_MEMBER_EMAILS);
                final DataStore<String> clubMembersEmails = new DataStoreArrays().toDataStore(memberEmails);

                final ArrayList<String> memberNames;
                memberNames = (ArrayList<String>) document.get(CLUB_MEMBER_NAMES);
                final DataStore<String> clubMemberNames = new DataStoreArrays().toDataStore(memberNames);

                final ArrayList<String> postTitles;
                postTitles = (ArrayList<String>) document.get(CLUB_POSTS_TITLE);
                final DataStore<String> clubPostsTitle = new DataStoreArrays().toDataStore(postTitles);

                final ArrayList<String> postDescription;
                postDescription = (ArrayList<String>) document.get(CLUB_POSTS_DESCRIPTION);
                final DataStore<String> clubPostsDescritption = new DataStoreArrays().toDataStore(postDescription);

                final ClubUserFactory clubUserFactory = new ClubUserFactory();
                final Club club = clubUserFactory.create(username, email, password, clubMembersEmails, clubMemberNames,
                        clubPostsTitle, clubPostsDescritption);

                club.setClubDescription(clubDescription);
                returnValue = club;
            }
        }
        catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
        }
        return returnValue;
    }

    @Override
    public void updateClubMembers(Club club) {
        // method updates the club data including the updated student list.
        final DocumentReference docRef = db.collection(CLUBS).document(club.getEmail());

        final ApiFuture<WriteResult> writeEmails = docRef.update(CLUB_MEMBER_EMAILS,
                club.getClubMembersEmails().toArrayList());

        final ApiFuture<WriteResult> writeNames = docRef.update(CLUB_MEMBER_NAMES,
                club.getClubMembersNames().toArrayList());
        try {
            logger.log(Level.INFO, UPDATE_TIME + writeEmails.get().getUpdateTime());
            logger.log(Level.INFO, UPDATE_TIME + writeNames.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
        }
    }

    @Override
    public DataStore<Club> getAllClubs() {
        final CollectionReference clubsRef = db.collection(CLUBS);
        final DataStoreArrays<Club> allClubs = new DataStoreArrays<>();
        logger.log(Level.INFO, "reached get all clubs call");
        try {
            final ApiFuture<QuerySnapshot> query = clubsRef.get();
            final QuerySnapshot querySnapshot = query.get();
            final List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            logger.log(Level.INFO, Integer.toString(documents.size()));

            for (DocumentSnapshot document : documents) {
                final String username = document.getString(USERNAME);
                final String emailVal = document.getString("email");
                final String password = document.getString(PASSWORD);
                final String clubDescription = document.getString(CLUB_DESCRIPTION);
                // Club's members and Posts information
                final ArrayList<String> memberEmails;
                memberEmails = (ArrayList<String>) document.get(CLUB_MEMBER_EMAILS);
                final DataStore<String> clubMembersEmails = new DataStoreArrays().toDataStore(memberEmails);

                final ArrayList<String> memberNames;
                memberNames = (ArrayList<String>) document.get(CLUB_MEMBER_NAMES);
                final DataStore<String> clubMemberNames = new DataStoreArrays().toDataStore(memberNames);

                final ArrayList<String> postTitles;
                postTitles = (ArrayList<String>) document.get(CLUB_POSTS_TITLE);
                final DataStore<String> clubPostsTitle = new DataStoreArrays().toDataStore(postTitles);

                final ArrayList<String> postDescription;
                postDescription = (ArrayList<String>) document.get(CLUB_POSTS_DESCRIPTION);
                final DataStore<String> clubPostsDescritption = new DataStoreArrays().toDataStore(postDescription);

                final ClubUserFactory clubUserFactory = new ClubUserFactory();
                final Club club = clubUserFactory.create(username, emailVal, password, clubMembersEmails,
                        clubMemberNames, clubPostsTitle, clubPostsDescritption);

                club.setClubDescription(clubDescription);

                allClubs.add(club);
            }
        }
        catch (InterruptedException | ExecutionException ex) {
            ex.printStackTrace();
            // Handle exceptions appropriately
        }

        return allClubs;
    }
}
