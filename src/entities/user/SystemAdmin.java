package entities.user;

import entities.container.Container;
import entities.port.Port;
import service.User.UserInterface;
import service.User.implementation.UserImplement;

import java.util.List;

public class SystemAdmin extends User {
    public SystemAdmin(String ID, String username, String password) {
        super(ID, username, password);
    }

    private final UserInterface userImplement = new UserImplement(this); // delegation

    public List<Port> getAllPorts() {
        return userImplement.getAllPorts();
    }
    public Port getById(String id) {
        return userImplement.getPortById(id);
    }

    public Port updatePort(Port entity) {
        return userImplement.updatePort(entity);
    }

    public boolean deletePort(String id) {
        return userImplement.deletePort(id);
    }
    public Port createPort(Port entity) {
        return userImplement.createPort(entity);
    }

    public List<Container> getAllContainers(){ return userImplement.getAllContainers();}
    public Container getByContainerId(String id) {return userImplement.getContainerById(id);}
    public Container updateContainer(Container entity){ return userImplement.updateContainer(entity); }
    public boolean deleteContainer(String id) { return userImplement.deleteContainer(id); }
    public Container createContainer(Container entity) { return userImplement.createContainer(entity); }

}

