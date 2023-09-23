package entities.container;

import entities.container.Container;

import java.io.Serializable;

public class OpenTopContainer extends Container implements Serializable {
    private static final double SHIP_FUEL_CONSUMPTION = 2.8;
    private static final double TRUCK_FUEL_CONSUMPTION = 3.2;

    public OpenTopContainer(String ID, double weight) {
        super(ID, weight);
    }

    public OpenTopContainer(String ID, double weight, Object location) {
        super(ID, weight, location);
    }

    @Override
    public String toString() {
        return "OpenTopContainer{" + super.toString() + "}";
    }

    @Override
    public double getShipFuelConsumption() {
        return SHIP_FUEL_CONSUMPTION;
    }

    @Override
    public double getTruckFuelConsumption() {
        return TRUCK_FUEL_CONSUMPTION;
    }
}
