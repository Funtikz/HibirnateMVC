package org.example;

import Entity.Company;
import Entity.User;
import UtilityOpen.HibernateUtil;
import junit.framework.TestCase;
import lombok.var;
import org.hibernate.Session;

import org.hibernate.SessionFactory;
import org.junit.Test;

public class AppTest
    extends TestCase
{

    @Test
    public void testConnection(){
        try (SessionFactory sessionFactory = HibernateUtil.buildSession();
             Session session = sessionFactory.openSession();){
            session.beginTransaction();

            Company gogole = Company.builder()
                    .name("Gogole")
                    .build();


            User user1 = User.builder()
                    .firstname("Nikita")
                    .lastname("Sherbakov")
                    .salary(123.2)
                    .company(gogole)
                    .build();

            session.save(gogole);
            session.save(user1);

            session.getTransaction().commit();

        }
    }
}
