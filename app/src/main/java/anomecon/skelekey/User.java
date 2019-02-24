package anomecon.skelekey;

public class User {

    private int id;
    private String userName;
    private String email;
    private String password;

    //Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {

        return password;
    }
}
