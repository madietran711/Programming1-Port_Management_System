package service.CRUD;

import java.util.List;

public interface CRUDInterface<T, ID> {
    /**
     * Create a new object.
     *
     * @param entity The object to be created.
     * @return The created object.
     */
    T create(T entity);

    /**
     * Retrieve all objects of type T.
     *
     * @return A list of all objects of type T.
     */
    List<T> getAll();

    /**
     * Retrieve an object by its unique identifier.
     *
     * @param id The unique identifier of the object.
     * @return The object with the given identifier, or null if not found.
     */
    T getById(ID id);

    /**
     * Update an existing object.
     *
     * @param entity The object to be updated.
     * @return The updated object.
     */
    T update(T entity);

    /**
     * Delete an object by its unique identifier.
     *
     * @param id The unique identifier of the object to be deleted.
     */
    boolean delete(ID id);
}
