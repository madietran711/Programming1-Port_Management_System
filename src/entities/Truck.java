package entities;

import java.util.ArrayList;

public abstract class Truck extends Vehicle {

    public Truck(String id, String name, double currentFuel, double maxFuel, double currentCapacity,
            double carryingCapacity, Port currentPort, ArrayList<Container> containerList) {
        super(id, name, currentFuel, maxFuel, currentCapacity, carryingCapacity, currentPort, containerList);
    }

}
