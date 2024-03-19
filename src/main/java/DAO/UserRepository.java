package DAO;

import Entity.User;
import jakarta.persistence.EntityManager;
import org.hibernate.SessionFactory;


public class UserDao extends RepositoryBase<Integer, User> {
    public UserDao(EntityManager entityManager) {
        super(User.class, entityManager);
    }
}
