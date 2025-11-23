# QuizMaster - Java Exam & Grading System

### Overview

QuizMaster is a robust CLI (Command Line Interface) application built in Java. It simulates an online examination environment where administrators can manage a bank of questions and students can take quizzes with immediate feedback.

### Features

**_User Management_**  : Secure login and registration for students.

**_Question Bank Management (CRUD)_**  : Admins can Add and Delete questions.

**_Exam Engine_**  : Randomly serves questions to students and accepts inputs.

**_Automated Grading_**  : Calculates percentage and pass/fail status instantly.

**_History Log_**  : Saves every quiz attempt to a file for future reference.

### Technologies Used

#### Language:  **Java (JDK 8+)**

Concepts: Object-Oriented Programming (Polymorphism, Inheritance), Collections Framework (ArrayList, HashMap), File I/O (Object Serialization).

Tools: Git, VS Code / IntelliJ IDEA.

How to Run

Clone the Repository:

git clone [https://github.com/Anand2k29/QuizMaster.git](https://github.com/Anand2k29/QuizMaster.git)


Compile the Code:

javac *.java


Run the Application:

java QuizMaster


### Usage Instructions

First Run: The system will create empty data files (questions.dat, users.dat) automatically.

Admin Access:

_Username_  : **admin**

_Password_   : **admin123**

_Student Access_  : Choose **"Register" **from the main menu to create a new student account.

Testing Strategy

Login as Admin: Add 3-4 questions to the system.

Register a Student: Create a new user.

Take Quiz: Login as the student and select "Take Quiz".

Verify Results: Check your score immediately. Log out and log back in to ensure the "My Results" history is saved.

Screenshots
## Screenshots

### Main Menu
![Menu Screenshot](screenshots/menu.png)

### Quiz Running
![Quiz Screenshot](screenshots/quiz.png)