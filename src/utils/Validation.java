package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Validation {
    public static boolean validatePortIDFormat(String id) {
        boolean isValid = id.matches("^p-\\d+$");
        while (!isValid) {
            System.out.println("Invalid ID format. Please enter the ID in the format 'p-number'");
            id = new Scanner(System.in).nextLine();
            isValid = id.matches("^p-\\d+$");
        }
        return isValid;
    }
    public static boolean validateTripIDFormat(String id) {
        boolean isValid = id.matches("^t-\\d+$");
        while (!isValid) {
            System.out.println("Invalid ID format. Please enter the ID in the format 't-number'");
            id = new Scanner(System.in).nextLine();
            isValid = id.matches("^t-\\d+$");
        }
        return isValid;
    }
    public static boolean validateContainerIDFormat(String id) {
        boolean isValid = id.matches("^c-\\d+$");
        while (!isValid) {
            System.out.println("Invalid ID format. Please enter the ID in the format 'c-number'");
            id = new Scanner(System.in).nextLine();
            isValid = id.matches("^c-\\d+$");
        }
        return isValid;
    }
    public static boolean validateTruckIDFormat(String id) {
        boolean isValid = id.matches("^tr-\\d+$");
        while (!isValid) {
            System.out.println("Invalid ID format. Please enter the ID in the format 'tr-number'");
            id = new Scanner(System.in).nextLine();
            isValid = id.matches("^tr-\\d+$");
        }
        return isValid;
    }
    public static boolean validateShipIDFormat(String id) {
        boolean isValid = id.matches("^sh-\\d+$");
        while (!isValid) {
            System.out.println("Invalid ID format. Please enter the ID in the format 'sh-number'");
            id = new Scanner(System.in).nextLine();
            isValid = id.matches("^sh-\\d+$");
        }
        return isValid;
    }

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        sdf.setLenient(false);

        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }


}
