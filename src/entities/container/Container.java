package entities.container;


import service.Container.ContainerInterface;
import service.Container.implementation.ContainerImplement;

import java.io.Serializable;

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
        return "Container{" +
                "ID='" + ID + '\'' +
                ", weight=" + weight +
                '}';
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
}
