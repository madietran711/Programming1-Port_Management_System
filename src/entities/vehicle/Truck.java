package entities.vehicle;

import java.util.Scanner;

public class Truck extends Vehicle {
    public boolean validateIDFormat(String id) {
        boolean isValid = id.matches("^tr-\\d+$");
        while (!isValid) {
            System.out.println("Invalid ID format. Please enter the ID in the format 'tr-number'");
            id = new Scanner(System.in).nextLine();
            isValid = id.matches("^tr-\\d+$");
        }
        return isValid;
    }

    @Override
    public void setID(String ID) {
        ID = validateIDFormat(ID) ? ID : "";
        super.setID(ID);
    }
}
