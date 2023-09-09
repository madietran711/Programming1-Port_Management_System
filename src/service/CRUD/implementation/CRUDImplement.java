package service.CRUD.implementation;

import service.CRUD.CRUDInterface;
import utils.DatFileMethods;

import java.util.Collections;
import java.util.List;

public class CRUDImplement<T, ID> implements CRUDInterface<T, ID> {
    private final String fileName;
    private final Class<T> entityType;

    public CRUDImplement(String fileName, Class<T> entityType) {
        this.fileName = fileName;
        this.entityType = entityType;
    }

    @Override
    public T create(T entity) {
        try {
            List<T> entities = getAll();
            entities.add(entity);
            DatFileMethods.writeAllLines(fileName, entities);
            return entity;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public List<T> getAll() {
        try {
            return DatFileMethods.readAllLines(fileName, entityType);
        } catch (Exception e) {
            System.out.println(e);
        }
        return Collections.emptyList();
    }

    @Override
    public T getById(ID id) {
        try {
            List<T> entities = getAll();
            for (T entity : entities) {
                if (entityType.getDeclaredMethod("getID").invoke(entity).equals(id)) {
                    return entity;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public T update(T entity) {
        try {
            List<T> entities = getAll();
            for (int i = 0; i < entities.size(); i++) {
                if (entityType.getDeclaredMethod("getID").invoke(entities.get(i)).equals(
                        entityType.getDeclaredMethod("getID").invoke(entity))) {
                    entities.set(i, entity);
                    DatFileMethods.writeAllLines(fileName, entities);
                    return entity;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public boolean delete(ID id) {
        try {
            List<T> entities = getAll();
            for (int i = 0; i < entities.size(); i++) {
                if (entityType.getDeclaredMethod("getID").invoke(entities.get(i)).equals(id)) {
                    entities.remove(i);
                    DatFileMethods.writeAllLines(fileName, entities);
                    return getById(id) == null;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
