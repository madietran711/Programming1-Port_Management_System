package entities.user;

import entities.container.Container;
import entities.port.Port;

import entities.trip.Trip;

import entities.vehicle.Vehicle;
import service.User.UserInterface;
import service.User.implementation.UserImplement;

import java.util.List;

public class SystemAdmin extends User {


    public SystemAdmin(String ID, String username, String password) {
        super(ID, username, password);
    }

    @Override
    public String toString() {
        return "SystemAdmin{} " + super.toString();
    }
}

