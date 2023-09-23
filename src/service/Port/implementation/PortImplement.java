package service.Port.implementation;

import entities.container.Container;
import entities.port.Port;
import entities.trip.Trip;
import entities.vehicle.Vehicle;
import service.CRUD.CRUDInterface;
import service.CRUD.implementation.CRUDImplement;
import service.Port.PortInterface;

import java.io.Serializable;
import java.util.List;

public class PortImplement implements PortInterface, Serializable {
    static CRUDInterface<Port, String> portRepository;
    static {
        portRepository = new CRUDImplement<Port, String>("Port.dat", Port.class);
    }

    private Port port; // Add an instance variable to store the Port instance.

    public PortImplement(Port port) {
        this.port = port;
    }



    @Override
    public Port create(Port entity) {
        portRepository.create(entity);
        return entity;
    }

    @Override
    public List<Port> getAll() {
       return portRepository.getAll();

    }

    @Override
    public Port getById(String id) {
        return portRepository.getById(id);
    }

    @Override
    public Port update(Port entity) {
        return portRepository.update(entity);
    }

    @Override
    public boolean delete(String id) {
        return portRepository.delete(id);
    }

    @Override
    public boolean deleteAll() {
        return portRepository.deleteAll();
    }


    @Override
    public int getTotalVehicleCount() {

        return   port.getVehicleList().size();
    }


    @Override
    public int getTotalContainerCount() {
        return port.getContainerList().size();
    }



    @Override
    public double calculateDistanceFromPort(Port port) {
        return 0;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {

    port.getVehicleList().add(vehicle);
    portRepository.update(port);


    }

    @Override
    public void removeVehicle(Vehicle vehicle) {

    }

    @Override
    public void addContainer(Container container) {

    }

    @Override
    public void removeContainer(Container container) {

    }

    @Override
    public void addTrip(Trip trip) {

    }

    @Override
    public void removeTrip(Trip trip) {

    }

    @Override
    public List<Trip> getTrafficRecord() {
        return null;
    }
}
