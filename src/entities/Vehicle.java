package entities;

import java.util.ArrayList;
import java.util.Map;

import service.Courier;

// NOTE: BasicTruck, ReeferTruck and TankerTruck extends directly from Vehicle, need to make change in the UML diagram
public class Vehicle implements Courier {
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
    public void loadContainer() {
        throw new UnsupportedOperationException("Unimplemented method 'loadContainer'");
    }

    @Override
    public void unloadContainer() {
        throw new UnsupportedOperationException("Unimplemented method 'unloadContainer'");
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("Unimplemented method 'move'");
    }

    @Override
    public void refuel() {
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
