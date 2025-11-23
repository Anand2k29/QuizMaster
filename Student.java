import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    private static final long serialVersionUID = 1L;
    
    // UPDATED: Added 'final' to this line to fix the warning
    private final List<QuizResult> history;

    public Student(String u, String p, String n) { 
        super(u, p, n); 
        this.history = new ArrayList<>();
    }
    
    public void addResult(QuizResult r) { history.add(r); }
    
    public List<QuizResult> getHistory() { return history; }
}