package entities;

import java.util.List;

import service.Station;

public class Port implements Station {
    private String id;
    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private double currentCapacity;
    private double maxCapacity;
    private boolean landingAbility; // what does this attribute do again?
    private List<Trip> tripList;
    private List<Vehicle> vehicleList;
    private List<Container> containerList;

    public Port(String id, String name, double latitude, double longitude, String description, double currentCapacity,
            double maxCapacity, boolean landingAbility, List<Trip> tripList, List<Vehicle> vehicleList,
            List<Container> containerList) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.currentCapacity = currentCapacity;
        this.maxCapacity = maxCapacity;
        this.landingAbility = landingAbility;
        this.tripList = tripList;
        this.vehicleList = vehicleList;
        this.containerList = containerList;
    }

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
