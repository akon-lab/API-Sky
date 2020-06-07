package domain.Login;

public class PlayerAuth {
    private String username;
    private String password;

    public PlayerAuth(String username,String password){
        setUsername(username);
        setPassword(password);
    }
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
