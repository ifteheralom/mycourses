# mycourses
Course management website using Java Servlet

# To run a test
1. Install mysql server `sudo apt-get install mysql-server` with user as "root" and password as "333".
2. Install mysql workbench `sudo apt-get install mysql-workbench`
3. Create tables:
```
CREATE TABLE users(
'user_name', 'varchar(45)',
'pass_word', 'varchar(45)',
'type', 'varchar(45)',
'full_name', 'varchar(45)',
'user_id', 'varchar(20)');

CREATE TABLE courses(
'course_id', 'varchar(20)',
'course_name', 'varchar(45)',
'course_teacher', 'varchar(45)');

CREATE TABLE enroll(
'course_id', 'varchar(20)',
'course_name', 'varchar(45)',
'course_teacher', 'varchar(45)',
'user_name', 'varchar(20)');

CREATE TABLE enrollments(
'st_id', 'varchar(20)',
'st_name', 'varchar(45)',
'course_id', 'varchar(45)');
```

4. Create some dummy users in users table
5. Run the project using Tomcat server using Eclipse.
