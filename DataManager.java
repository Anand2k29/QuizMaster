import java.io.*;
import java.util.*;

public class DataManager {
    private Map<String, User> users = new HashMap<>();
    private List<Question> questions = new ArrayList<>();
    private final String USERS_FILE = "users.dat";
    private final String QUESTIONS_FILE = "questions.dat";

    public void addUser(User u) { users.put(u.getUsername(), u); }
    
    public User authenticate(String u, String p) {
        User user = users.get(u);
        if(user != null && user.checkPassword(p)) return user;
        return null;
    }

    public boolean userExists(String u) { return users.containsKey(u); }

    public void addQuestion(Question q) { questions.add(q); }
    
    public void deleteQuestion(int id) {
        questions.removeIf(q -> q.getId() == id);
        System.out.println("Question deleted (if it existed).");
    }

    public List<Question> getQuestions() { return questions; }

    public void printQuestions() {
        if(questions.isEmpty()) System.out.println("No questions in bank.");
        for(Question q : questions) System.out.println(q);
    }
    
    public void printStudents() {
        System.out.println("--- Student List ---");
        for(User u : users.values()) {
            if(u instanceof Student) System.out.println(u.getName() + " (" + u.getUsername() + ")");
        }
    }

    @SuppressWarnings("unchecked")
    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USERS_FILE))) {
            users = (Map<String, User>) ois.readObject();
        } catch (Exception e) { }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(QUESTIONS_FILE))) {
            questions = (List<Question>) ois.readObject();
            if(!questions.isEmpty()) {
                int maxId = questions.stream().mapToInt(Question::getId).max().orElse(0);
                Question.setCounter(maxId + 1);
            }
        } catch (Exception e) { }
    }

    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (IOException e) { System.out.println("Error saving users: " + e.getMessage()); }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(QUESTIONS_FILE))) {
            oos.writeObject(questions);
        } catch (IOException e) { System.out.println("Error saving questions: " + e.getMessage()); }
    }
}