package entities;

import java.util.Date;
import java.util.List;

import service.Account;
import service.Truck;

public class User implements Account {
    private String username;
    private String password;

    @Override
    public boolean userLogin(String username, String password) {
        throw new UnsupportedOperationException("Unimplemented method 'userLogin'");
    }

    @Override
    public boolean resetPassword(String username, String oldpassword, String newpassword) {
        throw new UnsupportedOperationException("Unimplemented method 'resetPassword'");
    }

    @Override
    public List<Ship> getAllShipsInPort(Port port) {
        throw new UnsupportedOperationException("Unimplemented method 'getAllShipsInPort'");
    }

    @Override
    public List<Truck> getAllTrucksInPort(Port port) {
        throw new UnsupportedOperationException("Unimplemented method 'getAllTrucksInPort'");
    }

    @Override
    public List<Trip> getAllTripOnDate(Date date) {
        throw new UnsupportedOperationException("Unimplemented method 'getAllTripOnDate'");
    }

    @Override
    public List<Trip> getAllTripBetweenDate(Date date1, Date date2) {
        throw new UnsupportedOperationException("Unimplemented method 'getAllTripBetweenDate'");
    }
}
