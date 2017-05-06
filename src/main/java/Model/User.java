package Model;

import java.util.Hashtable;
import java.util.List;

/**
 * Created by histo on 06/03/2017.
 */
public class User {

    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private int rol;
    private String image;
    private List<Eetakemon> eetakemons;

    public User() {
    }
    public User(String name, String surname, String username, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(String name, String surname, String username, String password, String email, int rol, String image) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.rol = rol;
        this.email = email;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getRol() {
        return rol;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Eetakemon> getEetakemons() {
        return eetakemons;
    }

    public void setEetakemons(List<Eetakemon> eetakemons) {
        this.eetakemons = eetakemons;
    }
}
