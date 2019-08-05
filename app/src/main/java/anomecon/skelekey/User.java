package anomecon.skelekey;

public class User extends Content {
    //region User Declarations
    private int id;
    private String userName;
    private String email;
    private String password;
    //endregion

    //region Setters
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
    //endregion

    //region Getters
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
    //endregion
}
