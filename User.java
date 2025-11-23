import java.io.Serializable;

public abstract class User implements Serializable {
    private static final long serialVersionUID = 1L;
    protected String username;
    protected String password;
    protected String name;

    public User(String u, String p, String n) {
        this.username = u;
        this.password = p;
        this.name = n;
    }
    public String getUsername() { return username; }
    public String getName() { return name; }
    public boolean checkPassword(String p) { return password.equals(p); }
}