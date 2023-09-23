package service.Vehicle;

import entities.port.Port;
import entities.trip.Trip;
import entities.vehicle.Vehicle;
import entities.container.Container;
import service.CRUD.CRUDInterface;

import java.util.Map;

public interface VehicleInterface extends CRUDInterface<Vehicle, String> {

    public boolean loadContainer(Container container);

    public boolean unloadContainer();

    public boolean move(Trip trip);

    public boolean refuel();
public double calculateTotalWeight();


    // for the 4 methods above, shouldn't it return a boolean value to verify if the
    // method can be carried out
    public Map<Container, Integer> getTotalContainerWithType();

    public int getTotalContainer();

    public boolean canMoveToPortWithCurrentLoad(Port port);


    public void addVehicleToPort(Port currentPort);




//+ loadContainer() : boolean
//+ unloadContainer() : boolean
//+ move() : boolean
//+ refuel() : boolean
//+ getTotalContainerWithType(): Map<ContainerType, int>
//+ getTotalContainer(): int
//+ calculateWeight(): double
//+ calculateCurrentLoad(): double
//+ canMoveToPortWithCurrentLoad(): boolean


}
