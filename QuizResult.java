import java.io.Serializable;
import java.util.Date;

public class QuizResult implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // UPDATED: Added 'final' to these lines to fix the warnings
    private final int score;
    private final int total;
    private final Date date;

    public QuizResult(int score, int total) {
        this.score = score;
        this.total = total;
        this.date = new Date();
    }

    @Override
    public String toString() {
        return String.format("Date: %s | Score: %d/%d", date.toString(), score, total);
    }
}