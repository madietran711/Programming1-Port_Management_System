package entities.trip;

import entities.port.Port;
import entities.vehicle.Vehicle;

import enums.TripStatus;
import service.Trip.TripInterface;
import service.Trip.implementation.TripImplement;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

public class Trip implements Serializable {

    private final TripInterface tripImplement = new TripImplement(this); // Pass the current Trip implementation

    private String ID;

    private Vehicle trackingVehicle;
    private LocalDate departureDate;
    private LocalDate arrivalDate;
    private Port departurePort;
    private Port arrivalPort;
    private TripStatus status;

    public Trip() {
    }

    public Trip(String ID, Vehicle trackingVehicle, LocalDate departureDate, LocalDate arrivalDate, Port departurePort,
            Port arrivalPort, TripStatus status) {
        this.ID = ID;
        this.trackingVehicle = trackingVehicle;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
        setDeparturePort(departurePort);
        setArrivalPort(arrivalPort);
        this.status = status;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "ID='" + ID + '\'' +
                ", trackingVehicle=" + trackingVehicle +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", arrivalPort=" + arrivalPort.getName() +
                ", departurePort=" + departurePort.getName() +
                ", status=" + status + '\'' +
                '}';
    }

    // Getters and setters
    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public Vehicle getTrackingVehicle() {
        return trackingVehicle;
    }

    public void setTrackingVehicle(Vehicle trackingVehicle) {
        this.trackingVehicle = trackingVehicle;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Port getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(Port departurePort) {
        if (departurePort != null) {
            this.departurePort = departurePort;
            tripImplement.addTripToArricalPort(departurePort);
        }
    }

    public Port getArrivalPort() {
        return arrivalPort;
    }

    public void setArrivalPort(Port arrivalPort) {
        if (arrivalPort != null) {
            this.arrivalPort = arrivalPort;
            tripImplement.addTripToArricalPort(arrivalPort);
        }
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = TripStatus.valueOf(status.toUpperCase());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Trip trip = (Trip) o;
        return Objects.equals(ID, trip.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }
}
