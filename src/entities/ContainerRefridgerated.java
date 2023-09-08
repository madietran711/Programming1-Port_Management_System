package entities;

public class ContainerRefridgerated extends Container {
    private static double shipFuelConsumption;
    private static double truckFuelConsumption;

    public ContainerRefridgerated(String id, double weight) {
        super(id, weight);
    }

    public static double getShipFuelConsumption() {
        return shipFuelConsumption;
    }

    public static void setShipFuelConsumption(double shipFuelConsumption) {
        ContainerRefridgerated.shipFuelConsumption = shipFuelConsumption;
    }

    public static double getTruckFuelConsumption() {
        return truckFuelConsumption;
    }

    public static void setTruckFuelConsumption(double truckFuelConsumption) {
        ContainerRefridgerated.truckFuelConsumption = truckFuelConsumption;
    }
}
