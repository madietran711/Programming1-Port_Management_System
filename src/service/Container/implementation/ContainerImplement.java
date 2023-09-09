package service.Container.implementation;

import entities.Container;
import entities.Port;
import service.CRUD.CRUDInterface;
import service.CRUD.implementation.CRUDImplement;
import service.Container.ContainerInterface;

import java.util.List;

public class ContainerImplement implements ContainerInterface {
    static CRUDInterface<Container, String> containerRepository;
    static {
        containerRepository = new CRUDImplement<Container, String>("Container.dat", Container.class);
    }
    @Override
    public Container create(Container entity) {
containerRepository.create(entity);
        return entity;
    }

    @Override
    public List<Container> getAll() {
        return containerRepository.getAll();
    }

    @Override
    public Container getById(String id) {
        return containerRepository.getById(id);
    }

    @Override
    public Container update(Container entity) {
        return containerRepository.update(entity);
    }

    @Override
    public boolean delete(String id) {
        return containerRepository.delete(id);
    }
}
