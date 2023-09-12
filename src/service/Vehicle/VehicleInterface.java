package service.Vehicle;

import entities.port.Port;
import entities.vehicle.Vehicle;
import entities.container.Container;
import service.CRUD.CRUDInterface;

import java.util.Map;

public interface VehicleInterface extends CRUDInterface<Vehicle, String> {

    public void loadContainer();

    public void unloadContainer();

    public void move();

    public void refuel();

    // for the 4 methods above, shouldn't it return a boolean value to verify if the
    // method can be carried out
    public Map<Container, Integer> getTotalContainerWithType();

    public int getTotalContainer();

    public boolean canMoveToPortWithCurrentLoad();

    public void addVehicleToPort(Port currentPort);
}
