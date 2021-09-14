package ru.stqa.pft.tests;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.stqa.pft.model.ContactData;
import ru.stqa.pft.model.GroupData;

import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeClass
    protected void setUp() throws Exception {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
        }
    }

    @Test (enabled = false)
    public void testHbConnectionGroup() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery( "from GroupData" ).list();
        for (GroupData group : result) {
            System.out.println(group);
        }
        session.getTransaction().commit();
        session.close();

    }

    @Test
    public void testHbConnectionContact() {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //java.lang.IllegalArgumentException: org.hibernate.query.sqm.InterpretationException: Error interpreting query [from ContactData where deprecated = '0000-00-00 00:00:00']; this may indicate a semantic (user query) problem or a bug in the parser
        //3680 [main] DEBUG o.h.q.h.i.SemanticQueryBuilder - Encountered implicit select clause : fromContactDatawheredeprecated=0000-00-00 00:00:00
        //3686 [main] DEBUG org.hibernate.orm.query.hql - Unable to resolve unqualified attribute [deprecated] in local from-clause
        //3687 [main] DEBUG o.h.r.t.b.j.i.JdbcResourceLocalTransactionCoordinatorImpl - JDBC transaction marked for rollback-only (exception provided for stack trace)
        List<ContactData> result = session.createQuery( "from ContactData where deprecated = '0000-00-00'").list();
        //List<ContactData> result = session.createQuery( "from ContactData where lastname like 'Sadko'").list();
        for (ContactData contact : result) {
            System.out.println(contact);
        }
        session.getTransaction().commit();
        session.close();

    }

}
