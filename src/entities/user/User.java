package entities.user;

import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

import entities.container.Container;
import entities.port.Port;
import entities.trip.Trip;
import entities.vehicle.Vehicle;
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

    public List<Container> getAllContainers(){ return userImplement.getAllContainers();}
    public Container getByContainerId(String id) {return userImplement.getContainerById(id);}
    public Container updateContainer(Container entity){ return userImplement.updateContainer(entity); }
    public boolean deleteContainer(String id) { return userImplement.deleteContainer(id); }
    public Container createContainer(Container entity) { return userImplement.createContainer(entity); }

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

public  boolean removeAllTrips(){return userImplement.removeAllTrips();}
public  boolean removeAllVehicles(){return userImplement.removeAllVehicles();}
public  boolean removeAllContainers(){return userImplement.removeAllContainers();}
    public boolean removeAllPorts(){return userImplement.removeAllPorts();}
    public void viewPortActivities(Port port){userImplement.viewPortActivities(port);}
    public User userLogin(String username, String password) {
        return userImplement.userLogin(username, password);
    }

    }
