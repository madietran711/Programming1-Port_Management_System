package entities.vehicle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import entities.port.Port;
import entities.container.Container;
import service.Port.implementation.PortImplement;
import service.Vehicle.VehicleInterface;
import service.Vehicle.implementation.VehicleImplement;

// NOTE: BasicTruck, ReeferTruck and TankerTruck extends directly from Vehicle, need to make change in the UML diagram
public class Vehicle implements Serializable {

    private final VehicleInterface vehicleImplement = new VehicleImplement(this); // Pass the current User instance.
    // @NotNull
    // @Unique
    private String ID;
    private String name;
    private double fuelTankCapacity;
    private double currentFuel;
    private double carryingCapacity;
    private Port currentPort;
    private ArrayList<Container> containerList;

    @Override
    public String toString() {
        if (currentPort != null) {
return
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", currentFuel=" + currentFuel +
                ", fuelCapacity= " + fuelTankCapacity +
                ", carryingCapacity=" + carryingCapacity +
                ", currentPort=" + getCurrentPort().getName() +
                ", containerList=" + containerList;
    }
        return
                "ID='" + ID + '\'' +
                        ", name='" + name + '\'' +
                        ", currentFuel=" + currentFuel +
                        ", fuelCapacity= " + fuelTankCapacity +
                        ", carryingCapacity=" + carryingCapacity +
                        ", currentPort= null"  +
                        ", containerList=" + containerList;
    }

    public Vehicle() {
    }

    public Vehicle(String ID, String name, double fuelTankCapacity, double currentFuel, double carryingCapacity) {
        this.ID = ID;
        this.name = name;
        this.fuelTankCapacity = currentFuel;
        this.currentFuel = currentFuel;
        this.carryingCapacity = carryingCapacity;
    }

    public Vehicle(String ID, String name, double fuelTankCapacity, double currentFuel, double carryingCapacity,
            Port currentPort, ArrayList<Container> containerList) {
        this.ID = ID;
        this.name = name;
        this.fuelTankCapacity = fuelTankCapacity;
        this.currentFuel = currentFuel;
        this.carryingCapacity = carryingCapacity;
        this.currentPort = currentPort;
        setCurrentPort(currentPort);
        this.containerList = new ArrayList<>();
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

    public double getFuelTankCapacity() {
        return fuelTankCapacity;
    }

    public void setFuelTankCapacity(double fuelTankCapacity) {
        this.fuelTankCapacity = fuelTankCapacity;
    }

    public double getCurrentCapacity() {
        return vehicleImplement.calculateTotalWeight();
    }

    public double getCurrentFuel() {
        return currentFuel;
    }

    public void setCurrentFuel(double currentFuel) {
        this.currentFuel = currentFuel;
    }

    public double CarryingCapacity() {
        return carryingCapacity;
    }

    public void setCarryingCapacity(double carryingCapacity) {
        this.carryingCapacity = carryingCapacity;
    }

    public Port getCurrentPort() {
        PortImplement portImplement = new PortImplement(this.currentPort);
        return portImplement.getById(this.currentPort.getID());
    }

    public void setCurrentPort(Port currentPort) {
        if (currentPort != null) {
            this.currentPort = currentPort;
            vehicleImplement.addVehicleToPort(currentPort);
        }
    }

    public double getCarryingCapacity() {
        return carryingCapacity;
    }
public boolean loadContainer(Container container) {
        if (container != null) {
            return vehicleImplement.loadContainer(container);
        }
    return false;
}
public boolean unloadContainer(Container container) {
    if (container != null) {
        return vehicleImplement.unloadContainer();
    }
    return false;
}

    public ArrayList<Container> getContainerList() {
        return this.containerList;
    }

    public void setContainerList(ArrayList<Container> containerList) {
        this.containerList = containerList;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(ID, vehicle.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}


