package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class RepositoryBase<K extends Serializable, E>  implements Repository<K, E> {

    private Class<E> clazz;
    private final EntityManager entityManager;

    @Override
    public E save(E entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        Optional<E> entity = findById(id);
        entity.ifPresent(entityManager::remove);
    }

    @Override
    public void update(E entity) {
        entityManager.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        return Optional.ofNullable(entityManager.find(clazz,id));
    }

    @Override
    public List<E> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<E> query = cb.createQuery(clazz);
        query.from(clazz);
        return entityManager.createQuery(query)
                .getResultList();
    }


}
