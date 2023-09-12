package service.User;

import java.util.Date;
import java.util.List;

import entities.container.Container;
import entities.port.Port;
import entities.trip.Trip;
import entities.user.User;
import entities.vehicle.Ship;
import entities.vehicle.Truck;
import service.CRUD.CRUDInterface;

public interface UserInterface extends CRUDInterface<User, String> {

    public boolean userLogin(String username, String password);

    public boolean resetPassword(String username, String oldPassword, String newPassword);

    public List<Ship> getAllShipsInPort(Port port);

    public List<Truck> getAllTrucksInPort(Port port);

    public List<Trip> getAllTripOnDate(Date date);

    public List<Trip> getAllTripBetweenDate(Date date1, Date date2);


    // manager
    public List<Port> getAllPorts();

    public Port createPort(Port entity);

    public Port getPortById(String id);

    public Port updatePort(Port entity);

    public boolean deletePort(String id);

    public List<Container> getAllContainers();

    public Container getContainerById(String id);

    public Container updateContainer(Container entity);

    public boolean deleteContainer(String id);

    public Container createContainer(Container entity);


}

