package lk.ijse.psm.resources;

import lk.ijse.psm.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory buildSessionFactory(){

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .loadProperties("connection.properties").build();

        Metadata metadata = new MetadataSources(registry)
                .addAnnotatedClass(Client.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Payment.class)
                .addAnnotatedClass(Police.class)
                .addAnnotatedClass(Reception.class)
                .buildMetadata();

        return metadata.getSessionFactoryBuilder().build();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
