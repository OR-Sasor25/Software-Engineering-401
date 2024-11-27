
public class ManagerLogIn {
    private String id;
    private String password;

    
    public ManagerLogIn(String id, String password) {
        this.id = id;
        this.password = password;
    }

    // getters/setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // method to log in 
    public boolean login(String enteredId, String enteredPassword) {
        return this.id.equals(enteredId) && this.password.equals(enteredPassword);
    }
}
