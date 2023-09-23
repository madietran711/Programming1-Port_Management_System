package entities.vehicle;

import java.util.ArrayList;

import entities.container.Container;
import entities.port.Port;
import entities.vehicle.Truck;

public class ReeferTruck extends Truck {
    public ReeferTruck(String ID, String name, double fuelTankCapacity, double currentFuel, double carryingCapacity,
            Port currentPort, ArrayList<Container> containerList) {
        super(ID, name, fuelTankCapacity, currentFuel, carryingCapacity, currentPort, containerList);
    }
    @Override
    public String toString() {
        return "ReeferTruck{" + super.toString() +"}";
    }
}
