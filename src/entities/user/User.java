package entities.user;

import java.io.Serializable;
import java.util.List;

import service.User.UserInterface;
import service.User.implementation.UserImplement;

public class User implements Serializable {
    private final UserInterface userImplement = new UserImplement(this); // Pass the current User instance.//
    private String ID;
    private String username;
    private String password;


    public User() {
    }

    public User(String ID, String username, String password) {
        this.ID = ID;
        this.username = username;
        this.password = password;
    }



    @Override
    public String toString() {
        return "User{" +
                "ID='" + ID + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userImplement=" + userImplement +
                '}';
    }




}
