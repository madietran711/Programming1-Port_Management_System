package entities.user;

import java.io.Serializable;
import java.util.List;

import entities.container.Container;
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

    public List<Container> getAllContainers(){ return userImplement.getAllContainers();}
    public Container getByContainerId(String id) {return userImplement.getContainerById(id);}
    public Container updateContainer(Container entity){ return userImplement.updateContainer(entity); }
    public boolean deleteContainer(String id) { return userImplement.deleteContainer(id); }
    public Container createContainer(Container entity) { return userImplement.createContainer(entity); }


}
