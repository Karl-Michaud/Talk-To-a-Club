package data_access;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import entity.data_structure.DataStore;
import entity.data_structure.DataStoreArrays;
import entity.post.Announcement;
import entity.post.AnnouncementFactory;
import entity.post.Post;
import entity.user.Club;
import entity.user.ClubUserFactory;
import org.checkerframework.checker.units.qual.C;
import use_case.club_create_post.ClubCreatePostUserDataAccessInterface;
import use_case.club_get_members.ClubGetMembersUserDataAccessInterface;
import use_case.club_get_posts.ClubGetPostsDataAccessInterface;
import use_case.club_remove_member.ClubRemoveMemberClubDataAccessInterface;
import use_case.club_update_desc.ClubUpdateDescDataAccessInterface;
import use_case.explore_clubs.ClubExploreClubsDataAccessInterface;
import use_case.login.club_login.ClubLoginDataAccessInterface;
import use_case.signup.club_signup.ClubSignupUserDataAccessInterface;
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
        ClubSignupUserDataAccessInterface, ClubStudentJoinClubDataAccessInterface,
        ClubStudentLeaveClubDataAccessInterface, ClubExploreClubsDataAccessInterface, ClubGetPostsDataAccessInterface,
        ClubUpdateDescDataAccessInterface, StudentDislikeClubDataAccessInterface,
        StudentLikeClubDataAccessInterface, StudentShowPostsClubAccessInterface {
    private final Firestore db;
    private final String clubs = "clubs";
    private final String usernames = "username";

    public ClubFirestoreUserDataAccessObject() {
        this.db = FirestoreClient.getFirestore();
    }

    @Override
    public boolean existsByNameClub(String username) {
        final ApiFuture<QuerySnapshot> future = db.collection(clubs).whereEqualTo(usernames, username).get();
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
        final DocumentReference docRef = db.collection(clubs).document(email);
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
        final DocumentReference docRef = db.collection(clubs).document(club.getEmail());
        final ApiFuture<WriteResult> writeResult = docRef.update("clubDescription",
                club.getClubDescription().toString());
        try {
            System.out.println("Update time: " + writeResult.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
        }
    }

    @Override
    public void saveClub(Club user) {
        final DocumentReference docRef = db.collection(clubs).document(user.getEmail());
        final Map<String, Object> mapClub = new HashMap<>();
        mapClub.put("username", user.getUsername());
        mapClub.put("email", user.getEmail());
        mapClub.put("password", user.getPassword());
        mapClub.put("clubDescription", user.getClubDescription());
        mapClub.put("clubMembersEmails", user.getClubMembersEmails().toArrayList().stream().toList());
        mapClub.put("clubMembersNames", user.getClubMembersNames().toArrayList().stream().toList());
        mapClub.put("clubPostsTitle", user.getClubPostsTitle().toArrayList().stream().toList());
        mapClub.put("clubPostsDescription", user.getClubPostsDescription().toArrayList().stream().toList());
        mapClub.put("allPosts", new ArrayList<Map<String, Object>>().stream().toList());

        final ApiFuture<WriteResult> writeResult = docRef.set(mapClub);

        try {
            System.out.println("Update time: " + writeResult.get().getUpdateTime());
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
        final DocumentReference docRef = db.collection(clubs).document(club.getEmail());
        final ApiFuture<DocumentSnapshot> future = docRef.get();

        final ApiFuture<WriteResult> writeTitle = docRef.update("clubPostsTitle",
                club.getClubPostsTitle().toArrayList().stream().toList());
        final ApiFuture<WriteResult> writePostDescription = docRef.update("clubPostsDescription",
                club.getClubPostsDescription().toArrayList().stream().toList());

        try {
            final DocumentSnapshot document = future.get();
            final ArrayList<Map<String, Object>> clubPostValues =
                    (ArrayList<Map<String, Object>>) document.get("allPosts");
            final Map<String, Object> clubPost = new HashMap<>();
            clubPost.put("title", post.getTitle());
            clubPost.put("content", post.getContent());
            clubPost.put("dateOfPosting", post.dateOfPosting().toString());
            clubPost.put("timeOfPosting", post.timeOfPosting().toString());
            clubPost.put("userLiked", post.getLikes().toArrayList().stream().toList());
            clubPost.put("userDisliked", post.getDislikes().toArrayList().stream().toList());

            clubPostValues.add(clubPost);

            final ApiFuture<WriteResult> writePostListUpdate = docRef.update("allPosts",
                    clubPostValues);

            System.out.println("Update time: " + writePostListUpdate.get().getUpdateTime());

            System.out.println("Update time: " + writeTitle.get().getUpdateTime());
            System.out.println("Update time: " + writePostDescription.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Post> getPosts(Club club) {
        final DocumentReference docRef = db.collection(clubs).document(club.getEmail());
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        final ArrayList<Post> postValues = new ArrayList<>();

        try {
            final DocumentSnapshot document = future.get();
            final ArrayList<Map<String, Object>> clubPostValues =
                    (ArrayList<Map<String, Object>>) document.get("allPosts");
            for (Map<String, Object> clubPost : clubPostValues) {
                final AnnouncementFactory announcementFactory = new AnnouncementFactory();
                final String title = clubPost.get("title").toString();
                final String content = clubPost.get("content").toString();
                final ArrayList userLiked = (ArrayList) clubPost.get("userLiked");
                final ArrayList userDisliked = (ArrayList) clubPost.get("userDisliked");

                final Announcement post = announcementFactory.create(title, content,
                        (DataStoreArrays) new DataStoreArrays().toDataStore(userLiked),
                        (DataStoreArrays) new DataStoreArrays().toDataStore(userDisliked));
                post.setTimeOfPosting(LocalTime.parse(clubPost.get("timeOfPosting").toString()));
                post.setDateOfPosting(LocalDate.parse(clubPost.get("dateOfPosting").toString()));

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
        final DocumentReference docRef = db.collection(clubs).document(email);
        final ApiFuture<DocumentSnapshot> future = docRef.get();
        Club returnValue = null;
        try {
            final DocumentSnapshot document = future.get();
            if (document.exists()) {

                final String username = document.getString("username");
                final String password = document.getString("password");
                final String clubDescription = document.getString("clubDescription");
                // Club's members and Posts information
                final ArrayList<String> memberEmails;
                memberEmails = (ArrayList<String>) document.get("clubMembersEmails");
                final DataStore<String> clubMembersEmails = new DataStoreArrays().toDataStore(memberEmails);

                final ArrayList<String> memberNames;
                memberNames = (ArrayList<String>) document.get("clubMembersNames");
                final DataStore<String> clubMemberNames = new DataStoreArrays().toDataStore(memberNames);

                final ArrayList<String> postTitles;
                postTitles = (ArrayList<String>) document.get("clubPostsTitle");
                final DataStore<String> clubPostsTitle = new DataStoreArrays().toDataStore(postTitles);

                final ArrayList<String> postDescription;
                postDescription = (ArrayList<String>) document.get("clubPostsDescription");
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
        final DocumentReference docRef = db.collection(clubs).document(club.getEmail());

        final ApiFuture<WriteResult> writeEmails = docRef.update("clubMembersEmails",
                club.getClubMembersEmails().toArrayList());

        final ApiFuture<WriteResult> writeNames = docRef.update("clubMembersNames",
                club.getClubMembersEmails().toArrayList());
        try {
            System.out.println("Update time: " + writeEmails.get().getUpdateTime());
            System.out.println("Update time: " + writeNames.get().getUpdateTime());
        }
        catch (InterruptedException | ExecutionException ex) {
            // Handle exceptions appropriately
            ex.printStackTrace();
        }
    }

    @Override
    public DataStore<Club> getAllClubs() {
        final CollectionReference clubsRef = db.collection(clubs);
        final DataStoreArrays<Club> allClubs = new DataStoreArrays<>();
        System.out.println("reached get all clubs call");
        try {
            final ApiFuture<QuerySnapshot> query = clubsRef.get();
            final QuerySnapshot querySnapshot = query.get();
            final List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
            System.out.println(documents.size());

            for (DocumentSnapshot document : documents) {
                final String username = document.getString("username");
                final String emailVal = document.getString("email");
                final String password = document.getString("password");
                final String clubDescription = document.getString("clubDescription");
                // Club's members and Posts information
                final ArrayList<String> memberEmails;
                memberEmails = (ArrayList<String>) document.get("clubMembersEmails");
                final DataStore<String> clubMembersEmails = new DataStoreArrays().toDataStore(memberEmails);

                final ArrayList<String> memberNames;
                memberNames = (ArrayList<String>) document.get("clubMembersNames");
                final DataStore<String> clubMemberNames = new DataStoreArrays().toDataStore(memberNames);

                final ArrayList<String> postTitles;
                postTitles = (ArrayList<String>) document.get("clubPostsTitle");
                final DataStore<String> clubPostsTitle = new DataStoreArrays().toDataStore(postTitles);

                final ArrayList<String> postDescription;
                postDescription = (ArrayList<String>) document.get("clubPostsDescription");
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
