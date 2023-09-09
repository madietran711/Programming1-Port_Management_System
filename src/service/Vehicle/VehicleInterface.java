package service.Vehicle;

import entities.Vehicle;
import service.CRUD.CRUDInterface;

import java.util.List;
import java.util.Map;

public interface VehicleInterface extends CRUDInterface<Vehicle, String> {

    public void loadContainer();

    public void unloadContainer();

    public void move();

    public void refuel();

    // for the 4 methods above, shouldn't it return a boolean value to verify if the
    // method can be carried out
    public Map<entities.Container, Integer> getTotalContainerWithType();

    public int getTotalContainer();

    public boolean canMoveToPortWithCurrentLoad();
}
