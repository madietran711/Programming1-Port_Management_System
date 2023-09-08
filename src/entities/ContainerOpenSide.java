package entities;

public class ContainerOpenSide extends Container {
    public ContainerOpenSide(String id, double weight) {
        super(id, weight);
    }

    private static double shipFuelConsumption;
    private static double truckFuelConsumption;

    public static double getShipFuelConsumption() {
        return shipFuelConsumption;
    }

    public static void setShipFuelConsumption(double shipFuelConsumption) {
        ContainerOpenSide.shipFuelConsumption = shipFuelConsumption;
    }

    public static double getTruckFuelConsumption() {
        return truckFuelConsumption;
    }

    public static void setTruckFuelConsumption(double truckFuelConsumption) {
        ContainerOpenSide.truckFuelConsumption = truckFuelConsumption;
    }
}
