Project Statement: QuizMaster Exam Portal

1. Problem Statement

Educational institutions often spend excessive time manually creating, printing, and grading quizzes. This paper-based approach is prone to grading errors, resource wastage, and delays in providing feedback to students. There is a need for a digital solution that allows instructors to create and manage test banks dynamically and enables students to take exams with instant, automated grading.

2. Scope of the Project

QuizMaster is a Java-based console application designed to digitize the examination process. The scope of the project includes:

Administrator Module: A secure interface for instructors to create, update, and delete quiz questions.

Student Module: A user-friendly interface for students to take timed or self-paced quizzes.

Grading Engine: Logic to automatically calculate scores based on correct answers.

Data Persistence: Using file handling to ensure user profiles, questions, and result history are saved permanently.

3. Target Users

The system is designed for two primary user groups:

Administrators (Instructors/Faculty): Responsible for managing the curriculum and question bank.

Students: Users who access the system to assess their knowledge and view past performance.

4. High-Level Features

Role-Based Authentication: Distinct login flows for Admins and Students to ensure security.

Dynamic Question Bank: Admins can add Multiple Choice Questions (MCQs) with customizable options.

Instant Grading: The system compares student inputs with stored correct answers in real-time.

Progress Tracking: Students can view a history of their past quiz attempts and scores.

Data Storage: All data (Users, Questions, Results) is stored in local .dat files using Java Object Serialization, preventing data loss upon system exit.