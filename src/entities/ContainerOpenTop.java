package entities;

public class ContainerOpenTop extends Container {
    public ContainerOpenTop(String id, double weight) {
        super(id, weight);
    }

    private static double shipFuelConsumption;
    private static double truckFuelConsumption;

    public static double getShipFuelConsumption() {
        return shipFuelConsumption;
    }

    public static void setShipFuelConsumption(double shipFuelConsumption) {
        ContainerOpenTop.shipFuelConsumption = shipFuelConsumption;
    }

    public static double getTruckFuelConsumption() {
        return truckFuelConsumption;
    }

    public static void setTruckFuelConsumption(double truckFuelConsumption) {
        ContainerOpenTop.truckFuelConsumption = truckFuelConsumption;
    }
}
