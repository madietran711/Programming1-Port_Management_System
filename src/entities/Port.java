package entities;

import java.util.List;

import service.Station;

public class Port implements Station {
    private String ID;
    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private double currentCapacity;
    private double storingCapacity;
    private boolean landingAbility;
    // what does the attribute above do again?
    private List<Trip> tripList;
    private List<Vehicle> vehicleList;
    private List<Container> containerList;

    @Override
    public int getTotalVehicleCount() {
        throw new UnsupportedOperationException("Unimplemented method 'getTotalVehicleCount'");
    }

    @Override
    public List<Vehicle> getVehicleList() {
        throw new UnsupportedOperationException("Unimplemented method 'getVehicleList'");
    }

    @Override
    public int getTotalContainerCount() {
        throw new UnsupportedOperationException("Unimplemented method 'getTotalContainerCount'");
    }

    @Override
    public List<Container> getContainerList() {
        throw new UnsupportedOperationException("Unimplemented method 'getContainerList'");
    }

    @Override
    public double calculateDistanceFromPort(Port port) {
        throw new UnsupportedOperationException("Unimplemented method 'calculateDistanceFromPort'");
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Unimplemented method 'addVehicle'");
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        throw new UnsupportedOperationException("Unimplemented method 'removeVehicle'");
    }

    @Override
    public void addContainer(Container container) {
        throw new UnsupportedOperationException("Unimplemented method 'addContainer'");
    }

    @Override
    public void removeContainer(Container container) {
        throw new UnsupportedOperationException("Unimplemented method 'removeContainer'");
    }

    @Override
    public void addTrip(Trip trip) {
        throw new UnsupportedOperationException("Unimplemented method 'addTrip'");
    }

    @Override
    public void removeTrip(Trip trip) {
        throw new UnsupportedOperationException("Unimplemented method 'removeTrip'");
    }

    @Override
    public List<Trip> getTrafficRecord() {
        throw new UnsupportedOperationException("Unimplemented method 'getTrafficRecord'");
    }

}
