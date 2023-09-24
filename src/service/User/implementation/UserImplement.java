package service.User.implementation;

import entities.container.Container;
import entities.port.Port;
import entities.trip.Trip;
import entities.user.SystemAdmin;
import entities.user.User;
import entities.vehicle.Vehicle;
import enums.TripStatus;
import service.CRUD.CRUDInterface;
import service.CRUD.implementation.CRUDImplement;
import service.Container.implementation.ContainerImplement;
import service.Port.implementation.PortImplement;

import service.Trip.implementation.TripImplement;

import service.User.UserInterface;
import service.Vehicle.implementation.VehicleImplement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public boolean deleteAll() {
        return  userRepository.deleteAll();
    }

    @Override
    public User userLogin(String username, String password) {

        // check password and username match
        List<User> users = userRepository.getAll();
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean resetPassword(String username, String oldPassword, String newPassword) {
        return false;
    }

    public <T extends Vehicle> List<T> getAllVehiclesOfTypeInPort(Port port, Class<T> vehicleType) {
        return port.getVehicleList()
                .stream()
                .filter(vehicleType::isInstance)
                .map(vehicleType::cast)
                .collect(Collectors.toList());
    }

    @Override
    public List<Trip> getAllTripOnDate(LocalDate date) {
        TripImplement tripImplement = new TripImplement(new Trip());
        // get all trip
        List<Trip> tripList = tripImplement.getAll();
        // filter tripList by date
        List<Trip> tripListOnDate = tripList.stream()
                // check if trip is completed and arrival date is equal to the input date
                .filter(trip -> (trip.getArrivalDate().equals(date) && trip.getStatus() == TripStatus.COMPLETED) ||
                // check if trip is on going and departure date is equal to the input date
                        (trip.getDepartureDate().equals(date) && trip.getStatus() == TripStatus.ON_GOING) ||
                        // check if trip is on going and the input date is between departure date and
                        // arrival date
                        (trip.getDepartureDate().isBefore(date) && trip.getArrivalDate().isAfter(date)
                                && trip.getStatus() == TripStatus.ON_GOING))
                .collect(Collectors.toList());

        return tripListOnDate;

    }

    @Override
    public List<Trip> getAllTripBetweenDate(LocalDate date1, LocalDate date2) {
        TripImplement tripImplement = new TripImplement(new Trip());
        List<Trip> tripList = tripImplement.getAll();

        List<Trip> tripsBetweenDates = tripList.stream()
                // check if trip is completed and arrival date is between the input dates
                .filter(trip -> (trip.getArrivalDate().isAfter(date1) && trip.getArrivalDate().isBefore(date2)
                        && trip.getStatus() == TripStatus.COMPLETED) ||
                // check if trip is on going and departure date is between the input dates
                        (trip.getDepartureDate().isAfter(date1) && trip.getDepartureDate().isBefore(date2)
                                && trip.getStatus() == TripStatus.ON_GOING)
                        ||
                        // check if trip is on going and the input dates are between departure date and
                        // arrival date
                        (trip.getDepartureDate().isBefore(date1) && trip.getArrivalDate().isAfter(date2)
                                && trip.getStatus() == TripStatus.ON_GOING))
                .collect(Collectors.toList());

        return tripsBetweenDates;
    }

    @Override
    public List<Trip> calculateFuelUsageOnDate(Date date) {
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
    return containerImplement.update(entity);}

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

    @Override
    public boolean removeAllTrips() {
        TripImplement tripImplement = new TripImplement(new Trip());
        return tripImplement.deleteAll();
    }

    @Override
    public boolean removeAllVehicles() {
        VehicleImplement vehicleImplement = new VehicleImplement(new Vehicle());
        return vehicleImplement.deleteAll();

    }

    @Override
    public boolean removeAllContainers() {
        ContainerImplement containerImplement = new ContainerImplement(new Container());
        return containerImplement.deleteAll();

    }

    @Override
    public boolean removeAllPorts() {
        PortImplement portImplement = new PortImplement(new Port());
        return portImplement.deleteAll();

    }

    @Override
    public void viewPortActivities(Port port) {
        PortImplement portImplement = new PortImplement(port);
        portImplement.viewPortActivities();

    }




}
