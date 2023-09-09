package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

import service.User.implementation.UserImplement;
import service.Vehicle.VehicleInterface;
import service.Vehicle.implementation.VehicleImplement;

// NOTE: BasicTruck, ReeferTruck and TankerTruck extends directly from Vehicle, need to make change in the UML diagram
public class Vehicle implements Serializable {

    private final VehicleImplement vehicleImplement = new VehicleImplement(this); // Pass the current User instance.
    // @NotNull
    // @Unique
    private String id;
    private String name;
    private double currentFuel;
    private double maxFuel;
    private double currentCapacity;
    private double maxCapacity;
    private Port currentPort;
    private ArrayList<Container> containerList;

    public Vehicle() {
    }

    public Vehicle(String ID, String name, double currentFuel, double currentCapacity, double carryingCapacity,
            Port currentPort, ArrayList<Container> containerList) {
        this.ID = ID;
        this.name = name;
        this.currentFuel = currentFuel;
        this.currentCapacity = currentCapacity;
        this.carryingCapacity = carryingCapacity;
        this.currentPort = currentPort;
        this.containerList = containerList;
    }

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

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public double getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(double currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public Port getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(Port currentPort) {
        this.currentPort = currentPort;
    }

    public ArrayList<Container> getContainerList() {
        return containerList;
    }

    public void setContainerList(ArrayList<Container> containerList) {
        this.containerList = containerList;
    }
}
