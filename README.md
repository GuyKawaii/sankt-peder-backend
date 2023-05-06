# Startcode for Spring Boot Rest projects, with support for JWT Authentication/Authorization

Clone or fork the project
If cloned delete the hidden .git folder and do a new git init

Se thiss [short video](https://www.youtube.com/watch?v=aISFmtX-vfA)
I explains how to use it for the cars'R' Us project and but ALSO for future projects this semester where you need Security

### Short text-version of what the video above goes through
- Clone (prefered)/fork the project, and do what it takes to create you own git-folder, and after that, git-repo
- Create the MySQL database you plan to use for the project on your local MySQL instance
- Add the environment variables to Intellij, necessary to connect to the database. Remember to uncomment where these are read in **application.properties**, otherwise it will use H2.
- Rename (REFACTOR) the package name rename_me into something that makes sense
- If you only need plain users (username=primary key, email and password) use it as is
- If you need specialised users, extend the `UserWithRoles` class
- Default POST login-endpoint is here: **/api/auth/login**
  
  Add this JSON with the login request:
  ```
  {
  "username" : "user1",
  "password" : "test12"
  }
  ```
