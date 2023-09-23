package entities.container;

import entities.container.Container;

import java.io.Serializable;

public class DryStorageContainer  extends Container  implements Serializable{
    private static final double SHIP_FUEL_CONSUMPTION = 3.5;
    private static final double TRUCK_FUEL_CONSUMPTION = 4.6;

    public DryStorageContainer(String ID, double weight) {
        super(ID, weight);
    }

    public DryStorageContainer(String ID, double weight, Object location) {
        super(ID, weight, location);
    }

    @Override
    public String toString() {
        return "DryStorageContainer{" + super.toString() + "}";
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
