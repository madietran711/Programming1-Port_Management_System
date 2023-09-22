package entities.container;

import entities.container.Container;

import java.io.Serializable;

public class RefridgeratedContainer extends Container implements Serializable {
    private static final double SHIP_FUEL_CONSUMPTION = 4.5;
    private static final double TRUCK_FUEL_CONSUMPTION = 5.4;

    public RefridgeratedContainer(String ID, double weight) {
        super(ID, weight);
    }

    @Override
    public String toString() {
        return "RefridgeratedContainer{" + super.toString() + "}";
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
