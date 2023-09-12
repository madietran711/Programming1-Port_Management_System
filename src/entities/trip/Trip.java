package entities.trip;

import entities.port.Port;
import entities.vehicle.Vehicle;

import enums.TripStatus;
import service.Trip.TripInterface;
import service.Trip.implementation.TripImplement;

import java.io.Serializable;
import java.util.Date;

public class Trip implements Serializable {

    private final TripInterface tripImplement = new TripImplement(this); // Pass the current Trip implementation

    private String ID;

    private Vehicle trackingVehicle;
    private Date departureDate;
    private Date arrivalDate;
    private Port departurePort;
    private Port arrivalPort;
    private TripStatus status;


    public Trip() {
    }

    public Trip(String ID, Vehicle trackingVehicle, Date departureDate, Date arrivalDate, Port departurePort,
            Port arrivalPort, TripStatus status) {
        this.ID = ID;
        this.trackingVehicle = trackingVehicle;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.departurePort = departurePort;
        this.arrivalPort = arrivalPort;
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

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Port getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(Port departurePort) {
        this.departurePort = departurePort;
    }

    public Port getArrivalPort() {
        return arrivalPort;
    }

    public void setArrivalPort(Port arrivalPort) {
        this.arrivalPort = arrivalPort;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(String status) {

        this.status = TripStatus.valueOf( status.toUpperCase());
    }

}
