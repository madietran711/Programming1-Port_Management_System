package entities.user;

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

    private final UserInterface userImplement = new UserImplement(this); // delegation

    public List<Port> getAllPorts() {
        return userImplement.getAllPorts();
    }

    public Port getPortById(String id) {
        return userImplement.getPortById(id);
    }

    public Port updatePort(Port entity) {
        return userImplement.updatePort(entity);
    }

    public boolean deletePort(String id) {
        return userImplement.deletePort(id);
    }

    public Port createPort(Port entity) {
        return userImplement.createPort(entity);
    }


    public List<Trip> getAllTrips() {
        return userImplement.getAllTrips();
    }

    public Trip createTrip(Trip entity) {
        return userImplement.createTrip(entity);
    }

    public Trip getTripById(String id) {
        return userImplement.getTripById(id);
    }

    public Trip updateTrip(Trip entity) {
        return userImplement.updateTrip(entity);
    }

    public boolean deleteTrip(String id) {
        return userImplement.deleteTrip(id);
    }

    public List<Vehicle> getAllVehicles() {
        return userImplement.getAllVehicles();
    }
    public Vehicle createVehicle(Vehicle entity) {
        return userImplement.createVehicle(entity);
    }


    public Vehicle getVehicleById(String id) {
        return userImplement.getVehicleById(id);
    }

    public Vehicle updateVehicle(Vehicle entity) {
        return userImplement.updateVehicle(entity);
    }

    public boolean deleteVehicle(String id) {
        return userImplement.deleteVehicle(id);
    }


}
