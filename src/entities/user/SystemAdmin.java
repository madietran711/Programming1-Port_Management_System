package entities.user;

import entities.container.Container;
import entities.port.Port;

import entities.trip.Trip;

import entities.vehicle.Vehicle;
import service.User.UserInterface;
import service.User.implementation.UserImplement;

import java.util.List;

public class SystemAdmin extends User {

    private String username;
    private String password;

    public SystemAdmin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public SystemAdmin(String ID, String username, String password) {
        super(ID, username, password);
    }
}

