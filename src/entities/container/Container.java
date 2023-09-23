package entities.container;

import entities.vehicle.Vehicle;
import service.Container.ContainerInterface;
import service.Container.implementation.ContainerImplement;

import java.io.Serializable;
import java.util.Objects;

public class Container implements Serializable {
    // @NotNull
    // @Unique
    private String ID;
    private double weight;

    private final ContainerInterface containerImplement = new ContainerImplement(this);

    public Container(String ID, double weight) {
        this.ID = ID;
        this.weight = weight;

    }

    public Container() {

    }

    @Override
    public String toString() {
        return "ID='" + ID + '\'' +
                ", weight=" + weight;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getShipFuelConsumption() {
        return 0.0; // Default value if not overridden in child classes
    }

    public double getTruckFuelConsumption() {
        return 0.0; // Default value if not overridden in child classes
    }

    public double calculateFuelConsumption(Vehicle vehicle) {
        return containerImplement.calculateFuelConsumptionByWeightPerKm(vehicle);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Container container = (Container) o;
        return Objects.equals(ID, container.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
