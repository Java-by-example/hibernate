package com.github.javabyexample.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PersonEntityTest {
    final Configuration configuration = new Configuration();

    @Test
    public void test() {
        configuration.configure("hibernate.cfg.xml");
        SessionFactory sessionFactory = configuration.buildSessionFactory();

        final StatelessSession session1 = sessionFactory.openStatelessSession();
        final PersonEntity person1 = new PersonEntity();
        person1.setId(1L);
        person1.setName("person 1");
        session1.insert(person1);

        final StatelessSession session2 = sessionFactory.openStatelessSession();
        final PersonEntity person2 = new PersonEntity();
        person2.setId(2L);
        person2.setName("person 2");
        session2.insert(person2);
        session2.close();

        final StatelessSession session3 = sessionFactory.openStatelessSession();
        List<PersonEntity> result1 = ((List<PersonEntity>) session3.createQuery("select p from PersonEntity p").list());
        System.out.println(result1);
        session1.close();
        List<PersonEntity> result2 = ((List<PersonEntity>) session3.createQuery("select p from PersonEntity p").list());
        System.out.println(result2);
    }
}
