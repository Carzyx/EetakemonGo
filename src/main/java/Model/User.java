package Model;

/**
 * Created by histo on 06/03/2017.
 */
public class User {
    public int id;
    public String name;
    public String surname;
    public String username;
    public String password;
    public String email;

    public User(){

    }

    public User(String name, String surname, String username, String password, String email)
    {
        this.name = name;
        this.surname  = surname;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
