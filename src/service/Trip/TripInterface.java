package service.Trip;

import entities.port.Port;
import entities.trip.Trip;
import service.CRUD.CRUDInterface;

public interface TripInterface extends CRUDInterface<Trip, String> {

    boolean updateTripStatus();

    public void addTripToArricalPort(Port arrivalPort);

    public void addTripToDeparturePort(Port departurePort);
}
