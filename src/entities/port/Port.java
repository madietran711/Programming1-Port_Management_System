package entities.port;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.container.Container;
import entities.trip.Trip;
import entities.vehicle.Vehicle;
import service.Port.PortInterface;
import service.Port.implementation.PortImplement;
import java.util.Objects;


public class Port implements Serializable {

    private final PortInterface portImplement = new PortImplement(this); // Pass the current Port instance.

    private String ID;
    private String name;
    private double latitude;
    private double longitude;
    private String description;
    private double currentCapacity;
    private double storingCapacity;
    private boolean landingAbility;

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
        this.tripList = new ArrayList<>();
        this.vehicleList = new ArrayList<>();
        this.containerList = new ArrayList<>();
    }

    public Port(String ID, String name, double latitude, double longitude, String description, double currentCapacity,
            double storingCapacity, boolean landingAbility, List<Trip> tripList, List<Vehicle> vehicleList,
            List<Container> containerList) {

        this.ID = ID;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.currentCapacity = currentCapacity;
        this.storingCapacity = storingCapacity;
        this.landingAbility = landingAbility;
        this.tripList = tripList;
        this.vehicleList = vehicleList;
        this.containerList = containerList;
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
                ", landingAbility=" + landingAbility

                + ", tripList=" + tripList +
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
        return this.vehicleList;
    }

    public List<Trip> getTripList() {
        return this.tripList;
    }

    public List<Container> getContainerList() {
        return this.containerList;
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

    public int getTotalVehicleCount() {
        return portImplement.getTotalVehicleCount();
    }

    public int getTotalContainerCount() {
        return portImplement.getTotalContainerCount();
    }

    public double calculateDistanceFromPort(Port port) {
        double distanceBetweenPort = Math.pow((Math.pow((this.getLatitude() - port.getLatitude()), 2)
                + Math.pow((this.getLongitude() - port.getLongitude()), 2)), 0.5);
        return distanceBetweenPort;
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Port port = (Port) o;
        return Objects.equals(ID, port.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
