package entities.vehicle;

import java.util.ArrayList;

import entities.container.Container;
import entities.port.Port;
import entities.vehicle.Vehicle;

public class Truck extends Vehicle {
    @Override
    public String toString() {
        return "Truck{" + super.toString() + "}";
    }

    public Truck() {
    }

    public Truck(String ID, String name, double fuelTankCapacity, double currentFuel, double carryingCapacity,
                 Port currentPort, ArrayList<Container> containerList) {
        super(ID, name, fuelTankCapacity, currentFuel, carryingCapacity, currentPort, containerList);
    }
}
