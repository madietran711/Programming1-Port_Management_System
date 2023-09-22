package service.Container;

import entities.container.Container;
import entities.vehicle.Vehicle;
import service.CRUD.CRUDInterface;

public interface ContainerInterface extends CRUDInterface<Container, String> {
    public double calculateFuelConsumptionByWeightPerKm(Vehicle vehicle);
}
