package utils;

import entities.port.Port;
import entities.user.SystemAdmin;
import entities.vehicle.Vehicle;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
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
        ports.add(new Port("1", "Port 1", 1, 1, "Port 1", 1, 1, true, new ArrayList<>(),new ArrayList<>(), Collections.emptyList()));
        ports.add(new Port("2", "Port 2", 2, 2, "Port 2", 2, 2, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>()));
        writeAllLines("Port.dat", ports);
        SystemAdmin admin = new SystemAdmin("1", "admin", "admin");

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test get All method");
        admin.getAllPorts().forEach(System.out::println);




        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test create method");
       Port newPort = new Port("3", "Port 3", 3, 3, "Port 3", 3, 3, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        admin.createPort(newPort);
        ports = admin.getAllPorts();
         Vehicle newVehicle = new Vehicle("1", "Vehicle 1", 1,1,1,ports.get(1), new ArrayList<>());
        System.out.println(newVehicle);
        admin.updatePort(ports.get(1));
        admin.getAllPorts().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test update method");
        Port updatePort = new Port("3", "Port 3 Updated", 3, 3, "Port 3", 3, 3, true,new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
        admin.updatePort(updatePort);
        admin.getAllPorts().forEach(System.out::println);

        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Test delete method");
admin.deletePort("3");
        admin.getAllPorts().forEach(System.out::println);


    }
}
