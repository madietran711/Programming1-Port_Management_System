package entities;

import java.util.ArrayList;

public class TruckReefer extends Truck {

    public TruckReefer(String id, String name, double currentFuel, double maxFuel, double currentCapacity,
            double carryingCapacity, Port currentPort, ArrayList<Container> containerList) {
        super(id, name, currentFuel, maxFuel, currentCapacity, carryingCapacity, currentPort, containerList);
    }
}
