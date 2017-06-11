package DAL.EntityDataBase;

import Model.User;

/**
 * Created by histo on 06/03/2017.
 */
public class UserDto {

    private String name;
    private String surname;
    private String username;
    private String password;
    private String email;
    private int rol;
    private String image;

    public UserDto() {
    }

    public UserDto(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.rol = user.getRol();
        this.email = user.getEmail();
        this.image = user.getImage();
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

}
