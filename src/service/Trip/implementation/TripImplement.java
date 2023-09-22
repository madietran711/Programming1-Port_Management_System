package service.Trip.implementation;

import entities.trip.Trip;
import enums.TripStatus;
import service.CRUD.CRUDInterface;
import service.CRUD.implementation.CRUDImplement;
import service.Trip.TripInterface;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class TripImplement implements TripInterface, Serializable {

    static CRUDInterface<Trip, String> tripRepository;
    static {
        tripRepository = new CRUDImplement<Trip, String>("Trip.dat", Trip.class);
    }


    private Trip trip; // Add an instance variable to store the Trip instance.

    public TripImplement(Trip trip) {
        this.trip = trip;
    }


    @Override
    public Trip create(Trip entity) {
        tripRepository.create(entity);
        return entity;
    }

    @Override
    public List<Trip> getAll() {

        return tripRepository.getAll();


    }

    @Override
    public Trip getById(String id) {
        return tripRepository.getById(id);
    }

    @Override
    public Trip update(Trip entity) {
        return tripRepository.update(entity);
    }

    @Override
    public boolean delete(String id) {
        return tripRepository.delete(id);
    }


    @Override
    public boolean updateTripStatus() {
        LocalDate currentDate = LocalDate.now();
        LocalDate departureDate = this.trip.getDepartureDate();
        LocalDate arrivalDate = this.trip.getArrivalDate();
        // before the departure date
        if (currentDate.isBefore(departureDate) ){
            this.trip.setStatus(String.valueOf(TripStatus.PENDING));
            return true;
        }
        // during the trip
        else if (!currentDate.isBefore(departureDate) && !currentDate.isAfter(arrivalDate)) {
            this.trip.setStatus(String.valueOf(TripStatus.ON_GOING));
            return true;
        }
        // after the arrival date
        else if (currentDate.isAfter(arrivalDate)) {
            this.trip.setStatus(String.valueOf(TripStatus.COMPLETED));
            return true;
        }
        return false;
    }
}
