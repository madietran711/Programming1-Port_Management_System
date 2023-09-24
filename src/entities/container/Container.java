package entities.container;


import entities.port.Port;
import entities.trip.Trip;
import entities.vehicle.Vehicle;
import service.Container.ContainerInterface;
import service.Container.implementation.ContainerImplement;
import service.Port.implementation.PortImplement;
import service.Vehicle.implementation.VehicleImplement;
import utils.Validation;

import java.io.Serializable;
import java.util.Objects;

public class Container implements Serializable {
    // @NotNull
    // @Unique
    private String ID;
    private double weight;

    private Object location;


    private final ContainerInterface containerImplement = new ContainerImplement(this);

    public Container(String ID, double weight) {
        this.ID = ID;
        this.weight = weight;

    }

    public Container(String ID, double weight, Object location) {
        this.ID = ID;
        this.weight = weight;
        setLocation(location);
    }


    public Container() {

    }

    public void setID(String ID) {
        ID = Validation.validateContainerIDFormat(ID) ? ID : "";
        this.ID = ID;
    }
    @Override
    public String toString() {
        if (location instanceof Port) {
            return
                    "ID='" + ID + '\'' +
                    ", weight=" + weight + "location=" + ((Port) location).getName();}
        else if (location instanceof Vehicle){
            return
                    "ID='" + ID + '\'' +
                    ", weight=" + weight + "location=" + ((Vehicle) location).getName();}
        return
                "ID='" + ID + '\'' +
                ", weight=" + weight
                ;
        }


    public String getID() {
        return ID;
    }



    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getShipFuelConsumption() {
        return 0.0; // Default value if not overridden in child classes
    }

    public double getTruckFuelConsumption() {
        return 0.0; // Default value if not overridden in child classes
    }

    public double calculateFuelConsumption(Vehicle vehicle){
        return containerImplement.calculateFuelConsumptionByWeightPerKm(vehicle);
    }

    public Object getLocation() {
        return location;
    }

    public void setLocation(Object location) {
        if (location instanceof Port) {
            PortImplement portImplement = new PortImplement((Port) location);
           portImplement.addContainer(this);
            System.out.println("Container added to port");
        }
       if (location instanceof Vehicle){
           VehicleImplement vehicleImplement = new VehicleImplement((Vehicle) location);
              if(vehicleImplement.loadContainer(this))
                  System.out.println("Container added to vehicle");
              else
                  System.out.println("Container not added to vehicle");
       }
       this.location = location;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Container container = (Container) o;
        return Objects.equals(ID, container.ID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID);
    }

}
