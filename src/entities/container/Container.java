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
}
