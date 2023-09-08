package service;

import java.util.Date;
import java.util.List;

import entities.Port;
import entities.Ship;
import entities.Trip;

public interface Account {
    public boolean userLogin(String username, String password);

    public boolean resetPassword(String username, String oldpassword, String newpassword);

    public List<Ship> getAllShipsInPort(Port port);

    public List<Truck> getAllTrucksInPort(Port port);

    public List<Trip> getAllTripOnDate(Date date);

    public List<Trip> getAllTripBetweenDate(Date date1, Date date2);

}
