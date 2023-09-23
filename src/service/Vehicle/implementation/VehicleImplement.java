package service.Vehicle.implementation;

import entities.container.Container;
import entities.port.Port;
import entities.trip.Trip;
import entities.vehicle.Truck;
import entities.vehicle.Vehicle;
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
    public boolean loadContainer(List<Container> containerList) {
        List<Container> currentList = this.vehicle.getContainerList();
        // check if currentList + newList < carryingCapacity
        // if yes, add all container in containerList to currentList
        if (currentList.size() + containerList.size() <= this.vehicle.getCarryingCapacity()) {
            currentList.addAll(containerList);
            return true;
        }
        return false;
    }

    @Override
    public boolean unloadContainer() {
        // remove all container in currentList and return true if list is empty
        this.vehicle.getContainerList().clear();
        return this.vehicle.getContainerList().size() == 0;
    }

    @Override
    public boolean move(Trip trip) {
        TripImplement tripImplement = new TripImplement(trip);
        // check if current fuel is enough to move to port with distance
        if (!canMoveToPortWithCurrentLoad(trip.getArrivalPort())) {
            System.out.println("Trip cannot be carried out");
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
        List<Container> containerList = this.vehicle.getContainerList();
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
        if (this.vehicle instanceof Truck && !port.isLandingAbility()) {
            System.out.println("This port cannot receive truck");
            return false;
        }
        // check if currentLoad < carryingCapacity
        if (this.vehicle.getCurrentCapacity() > this.vehicle.getCarryingCapacity()) {
            // check if port can receive this vehicle
            System.out.println("Vehicle is overload");
        }
        // check if current fuel is enough to move to port with distance
        // calculate fuel needed to move to port
        double totalFuelConsumptionPerKm = this.vehicle.getContainerList()
                .stream()
                .mapToDouble(container -> container.calculateFuelConsumption(this.vehicle))
                .sum();
        double distance = this.vehicle.getCurrentPort().calculateDistanceFromPort(port);
        double fuelNeeded = totalFuelConsumptionPerKm * distance;
        if (this.vehicle.getCurrentFuel() < fuelNeeded) {
            System.out.println("Not enough fuel");
            return false;
        }
        return true;
    }

    @Override
    public void addVehicleToPort(Port currentPort) {
        PortImplement portImplement = new PortImplement(currentPort);
        portImplement.addVehicle(vehicle);
    }
}
