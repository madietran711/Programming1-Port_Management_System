package entities.vehicle;

import java.io.Serializable;
import java.util.ArrayList;

import entities.port.Port;
import entities.container.Container;
import service.Vehicle.VehicleInterface;
import service.Vehicle.implementation.VehicleImplement;

// NOTE: BasicTruck, ReeferTruck and TankerTruck extends directly from Vehicle, need to make change in the UML diagram
public class Vehicle implements Serializable {

    private final VehicleInterface vehicleImplement = new VehicleImplement(this); // Pass the current User instance.
    // @NotNull
    // @Unique
    private String ID;
    private String name;
    private double currentFuel;
    private double currentCapacity;
    private double carryingCapacity;
    private Port currentPort;
    private ArrayList<Container> containerList;

    @Override
    public String toString() {
        return "Vehicle{" +

                ", ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", currentFuel=" + currentFuel +
                ", currentCapacity=" + currentCapacity +
                ", carryingCapacity=" + carryingCapacity +
                ", currentPort=" + getCurrentPort().getName() +
                ", containerList=" + containerList +
                '}';
    }

    public Vehicle() {
    }

    public Vehicle(String ID, String name, double currentFuel, double currentCapacity, double carryingCapacity) {
        this.ID = ID;
        this.name = name;
        this.currentFuel = currentFuel;
        this.currentCapacity = currentCapacity;
        this.carryingCapacity = carryingCapacity;
    }

    public Vehicle(String ID, String name, double currentFuel, double currentCapacity, double carryingCapacity, Port currentPort, ArrayList<Container> containerList) {
        this.ID = ID;
        this.name = name;
        this.currentFuel = currentFuel;
        this.currentCapacity = currentCapacity;
        this.carryingCapacity = carryingCapacity;
        this.currentPort = currentPort;
       setCurrentPort(currentPort);
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
      if (currentPort != null) {
        this.currentPort = currentPort;
        vehicleImplement.addVehicleToPort(currentPort);}
    }

    public ArrayList<Container> getContainerList() {
        return containerList;
    }

    public void setContainerList(ArrayList<Container> containerList) {
        this.containerList = containerList;
    }
}
