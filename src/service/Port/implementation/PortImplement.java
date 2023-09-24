package service.Port.implementation;

import entities.container.Container;
import entities.port.Port;
import entities.trip.Trip;
import entities.vehicle.Vehicle;
import enums.TripStatus;
import service.CRUD.CRUDInterface;
import service.CRUD.implementation.CRUDImplement;
import service.Port.PortInterface;
import service.Trip.implementation.TripImplement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
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

        return port.getVehicleList().size();
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
    public void viewPortActivities() {
        // get all trips that have this port as destination or origin
        TripImplement tripImplement = new TripImplement(new Trip());
        List<Trip> portDepartureTrips = tripImplement.getAll().stream().filter(trip -> trip.getDeparturePort().equals(this.port)).toList();

        List<Trip> portArrivalTrips = tripImplement.getAll().stream().filter(trip -> trip.getArrivalPort().equals(this.port)).toList();

        // get all trips that about to arrive but not yet arrived
        List<Trip> portArrivalTripsNotYetArrived = portArrivalTrips.stream().filter(trip -> trip.getStatus().equals(TripStatus.ON_GOING)).toList();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Port " + this.port.getName() + " is about to arrive " + portArrivalTripsNotYetArrived.size() + " trips");
        portArrivalTripsNotYetArrived.forEach(System.out::println);
        // get all trips that about to depart
        List<Trip> portDepartureTripsNotYetDeparted = portDepartureTrips.stream().filter(trip -> trip.getStatus().equals(TripStatus.PENDING)).toList();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Port " + this.port.getName() + " is about to depart " + portDepartureTripsNotYetDeparted.size() + " trips");
        portArrivalTripsNotYetArrived.forEach(System.out::println);
        if (portArrivalTripsNotYetArrived.size() != 0) {
        System.out.println("Please load the containers to the vehicles docked at this port if needed");}
        // get all trips that have arrived
        List<Trip> portArrivalTripsArrived = portArrivalTrips.stream().filter(trip -> trip.getStatus().equals(TripStatus.COMPLETED)).toList();
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Port " + this.port.getName() + " has arrived " + portArrivalTripsArrived.size() + " trips");
        portArrivalTripsArrived.forEach(System.out::println);
        if (portArrivalTripsArrived.size() != 0) {
        System.out.println("Please unload the following containers from the vehicles docked at this port if needed");}
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
        List<Trip> portTrafficRecord = new ArrayList<Trip>();
        List<Trip> portAllTrips = port.getTripList();
        LocalDate today = LocalDate.now();
        LocalDate cutoffDate = today.minusDays(7);
        for (Trip trip : portAllTrips) {
            if (trip.getArrivalDate().isBefore(cutoffDate)) {
                continue;
            } else {
                portTrafficRecord.add(trip);
            }
        }
        return portTrafficRecord;
    }
}
