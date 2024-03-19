package DAO;

import Entity.User;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public abstract class DaoBase <K extends Serializable, E>  implements Dao<K, E> {

    private Class<E> clazz;
    private final SessionFactory sessionFactory;

    @Override
    public E save(E entity) {
        @Cleanup Session session = sessionFactory.openSession();
        session.save(entity);
        return entity;
    }

    @Override
    public void delete(K id) {
        @Cleanup Session session = sessionFactory.openSession();
        session.delete(id);
        session.flush();
    }

    @Override
    public void update(E entity) {
        @Cleanup Session session = sessionFactory.openSession();
        session.merge(entity);
    }

    @Override
    public Optional<E> findById(K id) {
        @Cleanup Session session = sessionFactory.openSession();
        return Optional.ofNullable(session.get(clazz, id));
    }

    @Override
    public List<E> findAll() {
        @Cleanup Session session = sessionFactory.openSession();
        HibernateCriteriaBuilder cb = session.getCriteriaBuilder();
        JpaCriteriaQuery<E> query = cb.createQuery(clazz);
        query.from(clazz);
        return session.createQuery(query).getResultList();
    }


}
