package entities.user;

import entities.port.Port;
import entities.container.Container;
import service.User.implementation.UserImplement;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PortManager extends User {
    private final UserImplement userImplement = new UserImplement(this);
    private Port managingPort;

    public boolean addContainer(Container container) {
        throw new UnsupportedOperationException("Not implemented");
    };

    public boolean deleteContainer(Container container) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public boolean updateContainer(Container container) {
        throw new UnsupportedOperationException("Not implemented");
    }

    public List<Container> getContainerList() {
        throw new UnsupportedOperationException("Not implemented");
    }

    public double calculateFuelUsageOnDate(Date date) {
        throw new UnsupportedOperationException("Not implemented");
    };




    public Map<Container, Double> calculateWeightOfContainersByType() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
