package entities;

public class ContainerLiquid extends Container {
    public ContainerLiquid(String id, double weight) {
        super(id, weight);
    }

    private static double shipFuelConsumption;
    private static double truckFuelConsumption;

    public static double getShipFuelConsumption() {
        return shipFuelConsumption;
    }

    public static void setShipFuelConsumption(double shipFuelConsumption) {
        ContainerLiquid.shipFuelConsumption = shipFuelConsumption;
    }

    public static double getTruckFuelConsumption() {
        return truckFuelConsumption;
    }

    public static void setTruckFuelConsumption(double truckFuelConsumption) {
        ContainerLiquid.truckFuelConsumption = truckFuelConsumption;
    }
}
