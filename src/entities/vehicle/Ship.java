package entities.vehicle;

import java.util.ArrayList;

import entities.container.Container;
import entities.port.Port;
import entities.vehicle.Vehicle;
import utils.Validation;

public class Ship extends Vehicle {
    public Ship(String ID, String name, double fuelTankCapacity, double currentFuel, double carryingCapacity,
            Port currentPort, ArrayList<Container> containerList) {
        super(ID, name, fuelTankCapacity, currentFuel, carryingCapacity, currentPort, containerList);

    }
    @Override
    public void setID(String ID) {
        ID = Validation.validateShipIDFormat(ID) ? ID : "";
        super.setID(ID);
    }
    @Override
    public String toString() {
        return "Ship{ " + super.toString() + " }";
    }
}
