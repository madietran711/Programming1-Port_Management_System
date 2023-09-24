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


    public Port getManagingPort() {
        return managingPort;
    }

    public void setManagingPort(Port managingPort) {
        this.managingPort = managingPort;
    }

    public double calculateFuelUsageOnDate(Date date) {
        throw new UnsupportedOperationException("Not implemented");
    };



    public PortManager(String ID, String username, String password, Port managingPort) {
        super(ID, username, password);
        this.managingPort = managingPort;
    }





    public List<Container> getAllContainers() {
        return userImplement.getAllContainers();
    }

    public Container getContainerById(String id) {
        return userImplement.getContainerById(id);
    }

    public Container updateContainer(Container entity) {
        return userImplement.updateContainer(entity);
    }

    public boolean deleteContainer(String id) {
        return userImplement.deleteContainer(id);
    }
    public Container createContainer(Container entity) {
        return userImplement.createContainer(entity);
    }

    @Override
    public String toString() {
        return "PortManager{" +
                "managingPort=" + managingPort.getName() +
                "} " + super.toString();
    }
}
