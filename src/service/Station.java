package service;

import java.util.List;

import entities.Container;
import entities.Port;
import entities.Trip;
import entities.Vehicle;

public interface Station {
    public int getTotalVehicleCount();

    public List<Vehicle> getVehicleList();

    public int getTotalContainerCount();

    public List<Container> getContainerList();

    // for the method above, are we supposed to count the containers that has yet
    // been unloaded from the vehicles docked in the current port
    public double calculateDistanceFromPort(Port port);

    public void addVehicle(Vehicle vehicle);

    public void removeVehicle(Vehicle vehicle);

    public void addContainer(Container container);

    public void removeContainer(Container container);

    public void addTrip(Trip trip);

    public void removeTrip(Trip trip);

    // for the adder and removeer methods, probably should return a boolean to
    // indicate if it was carried out correctly or not
    public List<Trip> getTrafficRecord();
}
