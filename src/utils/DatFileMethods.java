package utils;

import entities.Port;
import entities.User;
import entities.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DatFileMethods implements Serializable {
    // get file path
    private static String getFilePath(String fileName) {
        // Assuming file is located in a folder named "dataFiles" relative to your project's root directory
        String absolutePath = "src" + File.separator + "dataFiles" + File.separator + fileName;
        return new File(absolutePath).getPath();
    }

    // write all data in a file
    public static <T> void writeAllLines(String fileName, List<T> data) {
        String filePath = getFilePath(fileName);
        // loop through the data and write each object to the file
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(filePath))) {
            for (T object : data) {
                outputStream.writeObject(object);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // read all data in a file
    public static <T> List<T> readAllLines(String fileName, Class<T> objectType) {

        String filePath = getFilePath(fileName);
        // initiate an empty array list to store all data
        List<T> allFileData = new ArrayList<>();
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath))) {
            // loop through the file until it reaches the end of file
            while (true) {
                try {
                    // read object from file
                    Object object = inputStream.readObject();
                    allFileData.add(objectType.cast(object));

                } catch (EOFException e) {
                    // End Of File Exception: break the loop
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return allFileData;
    }

    public static void main(String[] args) {
        List<Port> ports = new ArrayList<>();
        ports.add(new Port("1", "Port 1", 1, 1, "Port 1", 1, 1, true));
        ports.add(new Port("2", "Port 2", 2, 2, "Port 2", 2, 2, true));
        writeAllLines("Port.dat", ports);
        User admin = new User("1", "admin", "admin");

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        admin.getAllPorts().forEach(System.out::println);


        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test create method");
        Port newPort = new Port("3", "Port 3", 3, 3, "Port 3", 3, 3, true);
        admin.createPort(newPort);
        admin.getAllPorts().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test update method");
        Port updatePort = new Port("3", "Port 3 Updated", 3, 3, "Port 3", 3, 3, true);
        admin.updatePort(updatePort);
        admin.getAllPorts().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test delete method");
        admin.deletePort("3");
        admin.deletePort("1");
        admin.deletePort("2");
        admin.getAllPorts().forEach(System.out::println);

        // Vehicle
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle("1", "Vehicle 1", 1, 1, 1, null, null));
        vehicles.add(new Vehicle("2", "Vehicle 2", 2, 2, 2, null, null));
        writeAllLines("Vehicle.dat", vehicles);
        User adminVehicle = new User("1", "adminVehicle", "adminVehicle");

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        adminVehicle.getAllVehicles().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test create method");
        Vehicle newVehicle = new Vehicle("3", "Vehicle 3", 3, 3, 3, null, null);
        adminVehicle.createVehicle(newVehicle);
        adminVehicle.getAllVehicles().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test update method");
        Vehicle updateVehicle = new Vehicle("3", "Vehicle 3 Updated", 3, 3, 3, null, null);
        adminVehicle.updateVehicle(updateVehicle);
        adminVehicle.getAllVehicles().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test delete method");
        adminVehicle.deleteVehicle("1");
        adminVehicle.deleteVehicle("2");
        adminVehicle.deleteVehicle("3");
        adminVehicle.getAllVehicles().forEach(System.out::println);
    }
}
