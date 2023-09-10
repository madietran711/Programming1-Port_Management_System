package entities.trip;

import entities.port.Port;
import entities.vehicle.Vehicle;

import java.util.Date;

public class Trip {
    private Vehicle trackingVehicle;
    private Date departureDate;
    private Date arrivalDate;
    private Port departurePort;
    private Port arrivalPort;
    private String status;
}
