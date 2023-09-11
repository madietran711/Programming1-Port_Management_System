package entities;

import java.io.Serializable;
import java.util.List;

import service.User.implementation.UserImplement;

public class User implements Serializable {
    private final UserImplement userImplement = new UserImplement(this); // Pass the current User instance.//
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


    public List<Port> getAllPorts() {
        return userImplement.getAllPorts();
    }
    public Port createPort(Port entity) {
        return userImplement.createPort(entity);
    }


    public Port getVehicleById(String id) {
        return userImplement.getPortById(id);
    }

    public Port updatePort(Port entity) {
        return userImplement.updatePort(entity);
    }

    public boolean deletePort(String id) {
        return userImplement.deletePort(id);
    }

    public List<Vehicle> getAllVehicles() {
        return userImplement.getAllVehicles();
    }
    public Vehicle createVehicle(Vehicle entity) {
        return userImplement.createVehicle(entity);
    }


    public Vehicle getById(String id) {
        return userImplement.getVehicleById(id);
    }

    public Vehicle updateVehicle(Vehicle entity) {
        return userImplement.updateVehicle(entity);
    }

    public boolean deleteVehicle(String id) {
        return userImplement.deleteVehicle(id);
    }
}



