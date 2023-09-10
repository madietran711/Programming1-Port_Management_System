package service.Trip.implementation;

import entities.trip.Trip;
import service.CRUD.CRUDInterface;
import service.CRUD.implementation.CRUDImplement;
import service.Trip.TripInterface;

import java.util.List;

public class TripImplement implements TripInterface{
    static CRUDInterface<Trip, String> tripRepository;
    static {
        tripRepository = new CRUDImplement<Trip, String>("Trip.dat", Trip.class);
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
}
