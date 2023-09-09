package entities;

import java.io.Serializable;
import java.util.List;

import service.Port.PortInterface;
import service.Port.implementation.PortImplement;

public class Port implements Serializable {
    private final PortImplement portImplement = new PortImplement(this); // Pass the current Port instance.
    private String ID;
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

    public Port() {
    }

    public Port(String ID, String name, double latitude, double longitude, String description, double currentCapacity,
            double storingCapacity, boolean landingAbility) {
        this.ID = ID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.currentCapacity = currentCapacity;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
    }

    @Override
    public String toString() {
        return "Port{" +
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                ", currentCapacity=" + currentCapacity +
                ", storingCapacity=" + storingCapacity +
                ", landingAbility=" + landingAbility +
                ", tripList=" + tripList +
                ", vehicleList=" + vehicleList +
                ", containerList=" + containerList +
                '}';
    }

    // Getters and setters
    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public double getStoringCapacity() {
        return storingCapacity;
    }

    public void setStoringCapacity(double storingCapacity) {
        this.storingCapacity = storingCapacity;
    }

    public boolean isLandingAbility() {
        return landingAbility;
    }

    public void setLandingAbility(boolean landingAbility) {
        this.landingAbility = landingAbility;
    }

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public List<Trip> getTripList() {
        return tripList;
    }

    public List<Container> getContainerList() {
        return containerList;
    }

    public void setTripList(List<Trip> tripList) {
        this.tripList = tripList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }

    public void setContainerList(List<Container> containerList) {
        this.containerList = containerList;
    }

    // Implement PortInterface methods by delegating to portImplement

    public int getTotalVehicleCount() {
        return portImplement.getTotalVehicleCount();
    }

    public int getTotalContainerCount() {
        return portImplement.getTotalContainerCount();
    }

    public double calculateDistanceFromPort(Port port) {
        return portImplement.calculateDistanceFromPort(port);
    }

    public void addVehicle(Vehicle vehicle) {
        portImplement.addVehicle(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {
        portImplement.removeVehicle(vehicle);
    }

    public void addContainer(Container container) {
        portImplement.addContainer(container);
    }

    public void removeContainer(Container container) {
        portImplement.removeContainer(container);
    }

    public void addTrip(Trip trip) {
        portImplement.addTrip(trip);
    }

    public void removeTrip(Trip trip) {
        portImplement.removeTrip(trip);
    }

    public List<Trip> getTrafficRecord() {
        return portImplement.getTrafficRecord();
    }
}
