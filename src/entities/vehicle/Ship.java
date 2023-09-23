package entities.vehicle;

import entities.vehicle.Vehicle;

import java.util.Scanner;

public class Ship extends Vehicle {
    public boolean validateIDFormat(String id) {
        boolean isValid = id.matches("^sh-\\d+$");
        while (!isValid) {
            System.out.println("Invalid ID format. Please enter the ID in the format 'sh-number'");
            id = new Scanner(System.in).nextLine();
            isValid = id.matches("^sh-\\d+$");
        }
        return isValid;
    }

    @Override
    public void setID(String ID) {
        ID = validateIDFormat(ID) ? ID : "";
        super.setID(ID);
    }
}
