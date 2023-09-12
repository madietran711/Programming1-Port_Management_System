package service.User.implementation;

import entities.container.Container;
import entities.port.Port;
import entities.trip.Trip;
import entities.user.User;
import entities.vehicle.Ship;
import entities.vehicle.Truck;
import entities.vehicle.Vehicle;
import service.CRUD.CRUDInterface;
import service.CRUD.implementation.CRUDImplement;
import service.Container.implementation.ContainerImplement;
import service.Port.implementation.PortImplement;

import service.Trip.implementation.TripImplement;

import service.User.UserInterface;
import service.Vehicle.implementation.VehicleImplement;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class UserImplement implements UserInterface, Serializable {
    static CRUDInterface<User, String> userRepository;
    static {
        userRepository = new CRUDImplement<User, String>("User.dat", User.class);
    }

    private User user; // Add an instance variable to store the User instance.

    public UserImplement(User user) {
        this.user = user;
    }

    @Override
    public User create(User entity) {
        userRepository.create(entity);
        return entity;
    }


    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(String id) {
        return userRepository.getById(id);
    }

    @Override
    public User update(User entity) {
        return userRepository.update(entity);
    }

    @Override
    public boolean delete(String id) {
        return userRepository.delete(id);
    }

    @Override
    public boolean userLogin(String username, String password) {
        return false;
    }

    @Override
    public boolean resetPassword(String username, String oldPassword, String newPassword) {
        return false;
    }

    @Override
    public List<Ship> getAllShipsInPort(Port port) {
        return null;
    }

    @Override
    public List<Truck> getAllTrucksInPort(Port port) {
        return null;
    }

    @Override
    public List<Trip> getAllTripOnDate(Date date) {
        return null;
    }

    @Override
    public List<Trip> getAllTripBetweenDate(Date date1, Date date2) {
        return null;
    }

    @Override

    public List<Port> getAllPorts() {
        PortImplement portImplement = new PortImplement(new Port());
        return portImplement.getAll();
    }

    @Override
    public Port createPort(Port entity) {
        PortImplement portImplement = new PortImplement(new Port());
        return portImplement.create(entity);
    }

    @Override
    public Port getPortById(String id) {
        PortImplement portImplement = new PortImplement(new Port());
        return portImplement.getById(id);
    }

    @Override
    public Port updatePort(Port entity) {
        PortImplement portImplement = new PortImplement(new Port());
        return portImplement.update(entity);
    }

    @Override
    public boolean deletePort(String id) {
        PortImplement portImplement = new PortImplement(new Port());
        return portImplement.delete(id);
    }


    @Override
    public List<Trip> getAllTrips() {
        TripImplement tripImplement = new TripImplement(new Trip());
        return tripImplement.getAll();
    }

    @Override
    public Trip createTrip(Trip entity) {
        TripImplement tripImplement = new TripImplement(new Trip());
        return tripImplement.create(entity);
    }

    @Override
    public Trip getTripById(String id) {
        TripImplement tripImplement = new TripImplement(new Trip());
        return tripImplement.getById(id);
    }

    @Override
    public Trip updateTrip(Trip entity) {
        TripImplement tripImplement = new TripImplement(new Trip());
        return tripImplement.update(entity);
    }

    @Override
    public boolean deleteTrip(String id) {
        TripImplement tripImplement = new TripImplement(new Trip());
        return tripImplement.delete(id);
    }

    @Override
    public List<Vehicle> getAllVehicles(){
        VehicleImplement vehicleImplement = new VehicleImplement(new Vehicle());
        return vehicleImplement.getAll();
    }

    @Override
    public Vehicle createVehicle(Vehicle entity) {
        VehicleImplement vehicleImplement = new VehicleImplement(new Vehicle());
        return vehicleImplement.create(entity);
    }

    @Override
    public Vehicle getVehicleById(String id) {
        VehicleImplement vehicleImplement = new VehicleImplement(new Vehicle());
        return vehicleImplement.getById(id);
    }

    @Override
    public Vehicle updateVehicle(Vehicle entity) {
        VehicleImplement vehicleImplement = new VehicleImplement(new Vehicle());
        return vehicleImplement.update(entity);
    }

    @Override
    public boolean deleteVehicle(String id) {
        VehicleImplement vehicleImplement = new VehicleImplement(new Vehicle());
        return vehicleImplement.delete(id);
    }

    @Override
    public List<Container> getAllContainers() {
        ContainerImplement containerImplement = new ContainerImplement(new Container());
        return containerImplement.getAll();
    }

    @Override
    public Container getContainerById(String id) {
        ContainerImplement containerImplement = new ContainerImplement(new Container());
        return containerImplement.getById(id);
    }
    @Override
    public Container updateContainer(Container entity) {
        ContainerImplement containerImplement = new ContainerImplement(new Container());
        return containerImplement.update(entity);
    }

    @Override
    public boolean deleteContainer(String id) {
        ContainerImplement containerImplement = new ContainerImplement(new Container());
        return containerImplement.delete(id);
    }

    @Override
    public Container createContainer(Container entity) {
        ContainerImplement containerImplement = new ContainerImplement(new Container());
        return containerImplement.create(entity);
    }


}
