package service.Vehicle;

import entities.Vehicle;
import service.CRUD.CRUDInterface;

import java.util.List;
import java.util.Map;

public interface VehicleInterface extends CRUDInterface<Vehicle, String> {

    public void loadContainer();

    public boolean unloadContainer();

    public boolean move();

    public boolean refuel();

    public Map<entities.Container, Integer> getTotalContainerWithType();

    public int getTotalContainer();

    public boolean canMoveToPortWithCurrentLoad();
}
