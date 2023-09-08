package entities;

public class ContainerDryStorage extends Container {
    public ContainerDryStorage(String id, double weight) {
        super(id, weight);
    }

    private static double shipFuelConsumption;
    private static double truckFuelConsumption;

    public static double getShipFuelConsumption() {
        return shipFuelConsumption;
    }

    public static void setShipFuelConsumption(double shipFuelConsumption) {
        ContainerDryStorage.shipFuelConsumption = shipFuelConsumption;
    }

    public static double getTruckFuelConsumption() {
        return truckFuelConsumption;
    }

    public static void setTruckFuelConsumption(double truckFuelConsumption) {
        ContainerDryStorage.truckFuelConsumption = truckFuelConsumption;
    }
}
