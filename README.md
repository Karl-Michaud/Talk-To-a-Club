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
4) view: CreatePost View for clubs to create posts