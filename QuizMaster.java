import java.util.*;

public class QuizMaster {
    // We add 'final' here too to prevent warnings
    private static final Scanner scanner = new Scanner(System.in);
    private static final DataManager dataManager = new DataManager();
    private static User currentUser = null;

    public static void main(String[] args) {
        // 1. Load Data on Startup
        dataManager.loadData();
        
        // 2. Ensure Default Admin Exists
        if (!dataManager.userExists("admin")) {
            dataManager.addUser(new Admin("admin", "admin123", "Main Administrator"));
        }

        System.out.println("******************************************");
        System.out.println("      Welcome to QUIZMASTER PORTAL        ");
        System.out.println("******************************************");

        boolean running = true;
        while (running) {
            try {
                if (currentUser == null) {
                    running = showMainMenu();
                } else if (currentUser instanceof Admin) {
                    showAdminMenu();
                } else if (currentUser instanceof Student) {
                    showStudentMenu();
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear buffer
            }
        }

        // 3. Save Data on Exit
        dataManager.saveData();
        System.out.println("Data saved. Application closed.");
    }

    // --- MAIN MENU FLOW ---
    private static boolean showMainMenu() {
        System.out.println("\n1. Login");
        System.out.println("2. Register (Student)");
        System.out.println("3. Exit");
        System.out.print("Select Option: ");
        
        String choice = scanner.nextLine();
        // UPDATED: Using Modern "Rule Switch" (->) to fix warnings
        switch (choice) {
            case "1" -> login();
            case "2" -> register();
            case "3" -> { return false; }
            default -> System.out.println("Invalid choice. Try again.");
        }
        return true;
    }

    // --- ADMIN FLOW ---
    private static void showAdminMenu() {
        System.out.println("\n--- ADMIN DASHBOARD ---");
        System.out.println("1. Add New Question");
        System.out.println("2. View All Questions");
        System.out.println("3. Delete Question");
        System.out.println("4. View All Students");
        System.out.println("5. Logout");
        System.out.print("Select Option: ");

        String choice = scanner.nextLine();
        // UPDATED: Using Modern "Rule Switch" (->) to fix warnings
        switch (choice) {
            case "1" -> {
                System.out.print("Enter Question Text: ");
                String text = scanner.nextLine();
                String[] options = new String[4];
                for(int i=0; i<4; i++) {
                    System.out.print("Enter Option " + (char)('A'+i) + ": ");
                    options[i] = scanner.nextLine();
                }
                System.out.print("Correct Option (A/B/C/D): ");
                String input = scanner.nextLine().toUpperCase();
                if (input.isEmpty()) {
                    System.out.println("Invalid input.");
                    break; // Breaks out of the switch block
                }
                char correctChar = input.charAt(0);
                int correctIdx = correctChar - 'A';
                
                if(correctIdx >= 0 && correctIdx <= 3) {
                    dataManager.addQuestion(new Question(text, options, correctIdx));
                    System.out.println("Question added successfully!");
                } else {
                    System.out.println("Invalid option selected.");
                }
            }
            case "2" -> dataManager.printQuestions();
            case "3" -> {
                dataManager.printQuestions();
                System.out.print("Enter ID of question to delete: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());
                    dataManager.deleteQuestion(id);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid ID format.");
                }
            }
            case "4" -> dataManager.printStudents();
            case "5" -> {
                currentUser = null;
                System.out.println("Logged out.");
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    // --- STUDENT FLOW ---
    private static void showStudentMenu() {
        Student student = (Student) currentUser;
        System.out.println("\n--- STUDENT DASHBOARD (" + student.getName() + ") ---");
        System.out.println("1. Take Quiz");
        System.out.println("2. View My Results");
        System.out.println("3. Logout");
        System.out.print("Select Option: ");

        String choice = scanner.nextLine();
        // UPDATED: Using Modern "Rule Switch" (->) to fix warnings
        switch (choice) {
            case "1" -> startQuiz(student);
            case "2" -> {
                List<QuizResult> history = student.getHistory();
                if (history.isEmpty()) System.out.println("No past attempts found.");
                else {
                    System.out.println("\n--- Past Results ---");
                    for (QuizResult r : history) {
                        System.out.println(r);
                    }
                }
            }
            case "3" -> {
                currentUser = null;
                System.out.println("Logged out.");
            }
            default -> System.out.println("Invalid choice.");
        }
    }

    // --- QUIZ LOGIC ---
    private static void startQuiz(Student student) {
        List<Question> questions = dataManager.getQuestions();
        if(questions.isEmpty()) {
            System.out.println("No questions available in the question bank.");
            return;
        }

        int score = 0;
        System.out.println("\n--- STARTING QUIZ ---");
        
        // Shuffle questions for randomness
        Collections.shuffle(questions);

        // Take the quiz
        for (int i = 0; i < questions.size(); i++) {
            Question q = questions.get(i);
            System.out.println("\nQ" + (i+1) + ": " + q.getQuestionText());
            String[] opts = q.getOptions();
            for(int j=0; j<4; j++) {
                System.out.println((char)('A'+j) + ") " + opts[j]);
            }
            
            System.out.print("Your Answer: ");
            String ans = scanner.nextLine().toUpperCase();
            if(ans.isEmpty()) ans = " "; 
            
            int ansIdx = ans.charAt(0) - 'A';
            if (ansIdx == q.getCorrectOptionIndex()) {
                score++;
            }
        }

        // Calculate Result
        double percentage = ((double)score / questions.size()) * 100;
        System.out.println("\n--- QUIZ COMPLETED ---");
        System.out.printf("You scored: %d / %d (%.2f%%)\n", score, questions.size(), percentage);
        
        QuizResult result = new QuizResult(score, questions.size());
        student.addResult(result);
        System.out.println("Result saved to history.");
    }

    // --- AUTHENTICATION HELPER METHODS ---
    private static void login() {
        System.out.print("Username: ");
        String user = scanner.nextLine();
        System.out.print("Password: ");
        String pass = scanner.nextLine();

        User u = dataManager.authenticate(user, pass);
        if (u != null) {
            currentUser = u;
            System.out.println("Login Successful! Welcome " + u.getName());
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static void register() {
        System.out.print("Enter Username: ");
        String user = scanner.nextLine();
        if (dataManager.userExists(user)) {
            System.out.println("Username already exists.");
            return;
        }
        System.out.print("Enter Password: ");
        String pass = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine();

        dataManager.addUser(new Student(user, pass, name));
        System.out.println("Registration successful! Please login.");
    }
}