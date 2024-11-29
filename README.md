# csc207-final-project-club-app

## Contributors

- Roy Liu - royangliu 
- Frederik Brecht - FrederikBrecht 
- David Cen - piaaza + Pi-byt 
- Kabir Kumar - KabirK-05 
- Karl-Alexandre Michaud - Karl-Michaud 

## Week 1: 

### Roy: 

1) interface_adapter: club_signup and student_signup
2) use_case: club_signup and student_signup
3) data_access: UserDataAccessObject (in memory for now)
   
### David: 

1) interface_adapter: login
   
### Karl-Alexandre: 
1) use case: student_login and club_login
2) entity: implemented everything
    1) Post package and related classes and interfaces
    2) User package and related classes and interfaces
  
### Kabir: 
1) use case: student_login and club_login


## Week 2

### Roy:

1) interface_adapter: club_login and student_login revamp - separated into two controllers and presenters
2) use_case: club_login and student_login bug fixes
3) view: added checkbox inside LoginView for switching between club login and student login
4) app: setup to be able to run the signup and login views

### Karl-Alexandre

1) use case: student_login, club_login, club_create_post, club_get_members and club_remove_member
2) interface adapters: create_post
3) bug/checkstyle fixes
4) view: CreatePost View for clubs to create posts and added a LabelTextPanel class to avoid redundant code.

### Kabir:
1) data_access: FireStoreUserDataAccessObject implemented methods to persist data
2) setup Google cloud firestore


## Week 4:

### Roy:

1) Finished get posts, logout, ClubUpdateDescription use cases and interface adapters.
2) Created and Finished ClubLoggedInView, RemoveMemberPanel, PostTextPanel.
3) Edited the club login use case to return the club description to the presenter, and removed entities in output data
4) Added use cases to switch between the ClubLoggedInView and CreatePostView in ClubCreatePost use case
5) Added Logout use case into the Appbuilder

# 📦 Talk to a Club

(add your badges here)

> *Your documentation is a direct reflection of your software, so hold it to the same standards.*


## 🌟 Highlights

- Some functionality made easy!
- This problem handled
- etc.


## ℹ️ Overview

A paragraph explaining your work, who you are, and why you made it.


### ✍️ Authors

Mention who you are and link to your GitHub or organization's website.


## 🚀 Usage

*Show off what your software looks like in action! Try to limit it to one-liners if possible and don't delve into API specifics.*

```py
>>> import mypackage
>>> mypackage.do_stuff()
'Oh yeah!'
```


## ⬇️ Installation

Simple, understandable installation instructions!

```bash
pip install my-package
```

And be sure to specify any other minimum requirements like Python versions or operating systems.

*You may be inclined to add development instructions here, don't.*


## 💭 Feedback and Contributing

Add a link to the Discussions tab in your repo and invite users to open issues for bugs/feature requests.

This is also a great place to invite others to contribute in any ways that make sense for your project. Point people to your DEVELOPMENT and/or CONTRIBUTING guides if you have them.

