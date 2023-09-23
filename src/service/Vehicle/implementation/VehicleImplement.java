package service.Vehicle.implementation;

import entities.container.Container;
import entities.container.LiquidContainer;
import entities.container.RefridgeratedContainer;
import entities.port.Port;
import entities.trip.Trip;
import entities.vehicle.*;
import service.CRUD.CRUDInterface;
import service.CRUD.implementation.CRUDImplement;
import service.Port.implementation.PortImplement;
import service.Trip.implementation.TripImplement;
import service.Vehicle.VehicleInterface;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleImplement implements VehicleInterface, Serializable {
    static CRUDInterface<Vehicle, String> vehicleRepository;
    static {
        vehicleRepository = new CRUDImplement<Vehicle, String>("Vehicle.dat", Vehicle.class);
    }

    private Vehicle vehicle; // Add an instance variable to store the Vehicle instance.

    public VehicleImplement(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public Vehicle create(Vehicle entity) {
        vehicleRepository.create(entity);
        return entity;
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.getAll();
    }

    @Override
    public Vehicle getById(String id) {
        return vehicleRepository.getById(id);
    }

    @Override
    public Vehicle update(Vehicle entity) {
        return vehicleRepository.update(entity);
    }

    @Override
    public boolean delete(String id) {
        return vehicleRepository.delete(id);
    }

    @Override
    public boolean deleteAll() {
        return vehicleRepository.deleteAll();
    }

    @Override
    public boolean loadContainer(Container container) {
        // check if currentList + newList < carryingCapacity
        // if yes, add all container in containerList to currentList
        List<Container> currentList = this.vehicle.getContainerList();
        if (this.vehicle.getCurrentCapacity() == this.vehicle.getCarryingCapacity()) {
            System.out.println("Vehicle is overload");
            return false;
        }

        // check type of container and type of vehicle
        // RULE:
        // BasicTruck - DRY STORAGE, OPENSIDE, OPENTOP
        // ReeferTruck - REFRIDGERATED
        // TankTruck - LIQUID
        // Ship - ALL
        if (this.vehicle instanceof Ship) {
            this.vehicle.getContainerList().add(container);
            return  true;

        } else if (this.vehicle instanceof ReeferTruck) {
            if (container instanceof RefridgeratedContainer) {
                this.vehicle.getContainerList().add(container);
                update(this.vehicle);
                return  true;
            } else {
                System.out.println("This truck cannot load this type of container. Reefer truck can only load refridgerated container");
                return false;
            }
        } else if (this.vehicle instanceof TankerTruck) {
            if (container instanceof LiquidContainer) {
                this.vehicle.getContainerList().add(container);
                update(this.vehicle);
                return  true;
            } else {
                System.out.println("This truck cannot load this type of container. Tanker truck can only load liquid container");

                return false;
            }
        }
        else {
            if (container instanceof RefridgeratedContainer || container instanceof LiquidContainer) {
                System.out.println("This truck cannot load this type of container. Basic truck can only load dry storage, openside and opentop container");
                return false;
            } else {
                this.vehicle.getContainerList().add(container);
                update(this.vehicle);
                return true;
            }
        }

    }

    @Override
    public boolean unloadContainer() {
        // remove all container in currentList and return true if list is empty
        if (this.vehicle.getContainerList().size() == 0) {
            System.out.println("Vehicle is empty");
            return false;
        }
        List<Container> currentList = this.vehicle.getContainerList();
        Port currentPort = this.vehicle.getCurrentPort();
        if (currentPort == null) {
            System.out.println("Vehicle is not at any port");
            return false;
        }
        PortImplement portImplement = new PortImplement(currentPort);
        for (Container container : currentList) {
            portImplement.addContainer(container);
        }
        this.vehicle.getContainerList().clear();
        update(this.vehicle);
        return this.vehicle.getContainerList().size() == 0;
    }

    @Override
    public boolean move(Trip trip) {
        TripImplement tripImplement = new TripImplement(trip);
        // check if current fuel is enough to move to port with distance
        if (!canMoveToPortWithCurrentLoad(trip.getArrivalPort())) {
            System.out.println("Trip cannot be carried out!");
            return false;
        }
        // check trip date and move vehicle to port accordingly
        if (LocalDate.now().equals(trip.getDepartureDate())) {
            System.out.println("Vehicle is departing from Port: " + trip.getDeparturePort().getName()
                    + ". Destination: " + trip.getArrivalPort().getName() + ". Trip is on going");
            this.vehicle.setCurrentPort(trip.getDeparturePort());
            tripImplement.updateTripStatus();
        } else if (LocalDate.now().equals(trip.getArrivalDate())) {
            System.out
                    .println("Vehicle is arriving at Port: " + trip.getArrivalPort().getName() + ". Trip is completed");
            this.vehicle.setCurrentPort(trip.getArrivalPort());
            tripImplement.updateTripStatus();
        } else if (LocalDate.now().isBefore(trip.getDepartureDate())) {
            System.out.println("Vehicle is waiting at Port: " + trip.getDeparturePort().getName() + ". Destination: "
                    + trip.getArrivalPort().getName() + ". Trip is pending");
            this.vehicle.setCurrentPort(trip.getDeparturePort());
            tripImplement.updateTripStatus();
        } else {
            System.out.println("Vehicle is moving. Departure Port: " + trip.getDeparturePort().getName()
                    + ". Destination: " + trip.getArrivalPort().getName() + ". Trip is on going");
            this.vehicle.setCurrentPort(null);
            tripImplement.updateTripStatus();
        }

        return false;

    }

    @Override
    public boolean refuel() {
        this.vehicle.setCurrentFuel(this.vehicle.getFuelTankCapacity());
        return this.vehicle.getCurrentFuel() == this.vehicle.getFuelTankCapacity();

    }

    @Override
    public double calculateTotalWeight() {
        List<Container> containerList = getById(this.vehicle.getID()).getContainerList();
        // calculate weight of all container in containerList
        return containerList.stream()
                .mapToDouble(Container::getWeight)
                .sum();
    }

    @Override
    public Map<Container, Integer> getTotalContainerWithType() {
        List<Container> containerList = this.vehicle.getContainerList();
        // calculate number of container with each type
        // return a map with key is container and value is number of container
        Map<Container, Integer> map = new HashMap<>();
        for (Container container : containerList) {
            if (map.containsKey(container)) {
                map.put(container, map.get(container) + 1);
            } else {
                map.put(container, 1);
            }
        }

        return map;
    }

    @Override
    public int getTotalContainer() {
        return this.vehicle.getContainerList().size();
    }

    @Override
    public boolean canMoveToPortWithCurrentLoad(Port port) {

        // check if port can receive this vehicle

        System.out.println("Basic Info: ");
        System.out.println("Current port: " + this.vehicle.getCurrentPort().getName());
        System.out.println("Vehicle is moving to port: " + port.getName() + " with current load"+ this.vehicle.getCurrentCapacity());
        System.out.println("Result: ");
        if (this.vehicle instanceof Truck && !port.isLandingAbility()) {
            System.out.println("This port cannot receive truck. Try another port");
            return false;
        }
        // check if currentLoad < carryingCapacity
        if (this.vehicle.getCurrentCapacity() > this.vehicle.getCarryingCapacity()) {
            System.out.println("Vehicle current load: " + this.vehicle.getCurrentCapacity());
            System.out.println("Vehicle carrying capacity: " + this.vehicle.getCarryingCapacity());
            // check if port can receive this vehicle
            System.out.println("Vehicle is overload. Unload some containers to move to port");
        }
        // check if current fuel is enough to move to port with distance
        // calculate fuel needed to move to port
        double totalFuelConsumptionPerKm = this.vehicle.getContainerList()
                .stream()
                .mapToDouble(container -> container.calculateFuelConsumption(this.vehicle))
                .sum();
        double distance = this.vehicle.getCurrentPort().calculateDistanceFromPort( port);
        double fuelNeeded = totalFuelConsumptionPerKm * distance;
        if (this.vehicle.getCurrentFuel() < fuelNeeded) {
            System.out.println("Vehicle current fuel: " + this.vehicle.getCurrentFuel());
            System.out.println("Vehicle fuel needed: " + fuelNeeded);
            System.out.println("Not enough fuel. Refuel to move to port");
            return false;
        }
        System.out.println("Vehicle can move to port with current load");
        return true;
    }

    @Override
    public void addVehicleToPort(Port currentPort) {
        PortImplement portImplement = new PortImplement(currentPort);
        portImplement.addVehicle(vehicle);
    }
}
