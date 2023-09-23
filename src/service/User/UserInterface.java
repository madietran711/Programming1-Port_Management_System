package service.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import entities.container.Container;
import entities.port.Port;
import entities.trip.Trip;
import entities.user.User;
import entities.vehicle.Vehicle;
import service.CRUD.CRUDInterface;

public interface UserInterface extends CRUDInterface<User, String> {
    // + User(username : String, password : String) : void
    // + userLogin(username : String, password : String): boolean
    // + resetPassword (username : String, oldPassword : String, newPassword :
    // String): boolean
    // + getAllShipsInPort(port : Port): List<Ship>
    // + getAllTrucksInPort(port : Port): List<Truck>
    // + getAllTripInDay(date : Date): List<Trip>
    // + getAllTripBetweenDay(date1 : Date, date2 : Date): List<tTrip>
    // + calculateFuelUsageOnDate(date : Date): double
    // + calculateWeightOfContainersByType(): Map<ContainerType, Double>

    boolean userLogin(String username, String password);

    boolean resetPassword(String username, String oldPassword, String newPassword);

    <T extends Vehicle> List<T> getAllVehiclesOfTypeInPort(Port port, Class<T> vehicleType);

    List<Trip> getAllTripOnDate(LocalDate date);

    List<Trip> getAllTripBetweenDate(LocalDate date1, LocalDate date2);

    List<Trip> calculateFuelUsageOnDate(Date date);

    List<Port> getAllPorts();

    Port createPort(Port entity);

    Port getPortById(String id);

    Port updatePort(Port entity);

    boolean deletePort(String id);

    List<Trip> getAllTrips();

    Trip createTrip(Trip entity);

    Trip getTripById(String id);

    Trip updateTrip(Trip entity);

    boolean deleteTrip(String id);

    List<Vehicle> getAllVehicles();

    Vehicle createVehicle(Vehicle entity);

    Vehicle getVehicleById(String id);

    Vehicle updateVehicle(Vehicle entity);

    boolean deleteVehicle(String id);

    List<Container> getAllContainers();

    Container getContainerById(String id);

    Container updateContainer(Container entity);

    boolean deleteContainer(String id);

    Container createContainer(Container entity);

    boolean removeAllTrips();
    boolean removeAllVehicles();
    boolean removeAllContainers();
    boolean removeAllPorts();
    void viewPortActivities(Port port);

}
