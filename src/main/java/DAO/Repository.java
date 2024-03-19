package DAO;

import Entity.User;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao <K extends Serializable, E> {

    E save(E entity);
    void delete(K id);
    void update(E entity);
    Optional<E> findById(K id);

    User save(User entity);

    void delete(Integer id);

    void update(User entity);

    Optional<User> findById(Integer id);

    List<E> findAll();
}
