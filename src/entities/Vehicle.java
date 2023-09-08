package entities;

import java.util.ArrayList;
import java.util.Map;

import service.Courier;

// NOTE: BasicTruck, ReeferTruck and TankerTruck extends directly from Vehicle, need to make change in the UML diagram
public abstract class Vehicle implements Courier {
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

    public Vehicle(String id, String name, double currentFuel, double maxFuel, double currentCapacity,
            double carryingCapacity, Port currentPort, ArrayList<Container> containerList) {
        this.id = id;
        this.name = name;
        this.currentFuel = currentFuel;
        this.maxFuel = maxFuel;
        this.currentCapacity = currentCapacity;
        this.maxCapacity = maxCapacity;
        this.currentPort = currentPort;
        this.containerList = containerList;
    }

    @Override
    public boolean loadContainer() {
        throw new UnsupportedOperationException("Unimplemented method 'loadContainer'");
    }

    @Override
    public boolean unloadContainer() {
        throw new UnsupportedOperationException("Unimplemented method 'unloadContainer'");
    }

    @Override
    public boolean move() {
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public boolean refuel() {
        throw new UnsupportedOperationException("Unimplemented method 'refuel'");
    }

    @Override
    public int getTotalContainer() {
        throw new UnsupportedOperationException("Unimplemented method 'getTotalContainer'");
    }

    @Override
    public boolean canMoveToPortWithCurrentLoad() {
        throw new UnsupportedOperationException("Unimplemented method 'canMoveToPortWithCurrentLoad'");
    }

    @Override
    public Map<entities.Container, Integer> getTotalContainerWithType() {
        throw new UnsupportedOperationException("Unimplemented method 'getTotalContainerWithType'");
    }

}
