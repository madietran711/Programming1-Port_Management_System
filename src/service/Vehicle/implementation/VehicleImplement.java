package service.Vehicle.implementation;

import entities.Container;
import entities.User;
import entities.Vehicle;
import service.CRUD.CRUDInterface;
import service.CRUD.implementation.CRUDImplement;
import service.Vehicle.VehicleInterface;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class VehicleImplement implements VehicleInterface, Serializable {
    static CRUDInterface<Vehicle, String> vehicleRepository;
    static {
        vehicleRepository = new CRUDImplement<Vehicle, String>("Vehicle.dat", Vehicle.class);
    }

    private Vehicle vehicle; // Add an instance variable to store the Vehicle instance.
    public VehicleImplement(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    @Override
    public Vehicle create(Vehicle entity) {
        vehicleRepository.create(entity);
        return entity;
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.getAll();
    }

    @Override
    public Vehicle getById(String id) {
        return vehicleRepository.getById(id);
    }

    @Override
    public Vehicle update(Vehicle entity) {
        return vehicleRepository.update(entity);
    }

    @Override
    public boolean delete(String id) {
        return vehicleRepository.delete(id);
    }

    @Override
    public void loadContainer() {

    }

    @Override
    public void unloadContainer() {

    }

    @Override
    public void move() {

    }

    @Override
    public void refuel() {

    }

    @Override
    public Map<Container, Integer> getTotalContainerWithType() {
        return null;
    }

    @Override
    public int getTotalContainer() {
        return 0;
    }

    @Override
    public boolean canMoveToPortWithCurrentLoad() {
        return false;
    }
}
