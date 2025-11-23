import java.io.Serializable;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int idCounter = 1;
    
    // UPDATED: Added 'final' to these lines to fix the warnings
    private final int id;
    private final String text;
    private final String[] options;
    private final int correctOptionIndex;

    public Question(String text, String[] options, int correctIdx) {
        this.id = idCounter++;
        this.text = text;
        this.options = options;
        this.correctOptionIndex = correctIdx;
    }
    
    public static void setCounter(int val) { idCounter = val; }
    public int getId() { return id; }
    public String getQuestionText() { return text; }
    public String[] getOptions() { return options; }
    public int getCorrectOptionIndex() { return correctOptionIndex; }
    
    @Override
    public String toString() {
        return "ID " + id + ": " + text;
    }
}