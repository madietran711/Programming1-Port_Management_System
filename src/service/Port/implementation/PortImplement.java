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
        double distanceBetweenPort = Math.pow((Math.pow((this.port.getLatitude() - port.getLatitude()), 2)
                + Math.pow((this.port.getLongitude() - port.getLongitude()), 2)), 0.5);
        return distanceBetweenPort;
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        port.getVehicleList().add(vehicle);
        portRepository.update(port);
    }

    @Override
    public void removeVehicle(Vehicle vehicle) {
        port.getVehicleList().remove(vehicle);
        portRepository.update(port);
    }

    @Override
    public void addContainer(Container container) {
        port.getContainerList().add(container);
        portRepository.update(port);
    }

    @Override
    public void removeContainer(Container container) {
        port.getContainerList().remove(container);
        portRepository.update(port);
    }

    @Override
    public void addTrip(Trip trip) {
        port.getTripList().add(trip);
        portRepository.update(port);
    }

    @Override
    public void removeTrip(Trip trip) {
        port.getTripList().remove(trip);
        portRepository.update(port);
    }

    @Override
    public List<Trip> getTrafficRecord() {
        return null;
    }
}
