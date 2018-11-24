package com.aws.util;

/**
 * @author argu
 */
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (null != sessionFactory)
            return sessionFactory;
        
        Configuration configuration = new Configuration();

        String jdbcUrl = "jdbc:mysql://"
                + "score.cfracnybfkym.us-east-1.rds.amazonaws.com"
                + "/"
                + "statistics";
        
        configuration.setProperty("hibernate.connection.url", "score.cfracnybfkym.us-east-1.rds.amazonaws.com");
        configuration.setProperty("hibernate.connection.username", "vikash");
        configuration.setProperty("hibernate.connection.password", "qwerty1234");

        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        try {
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (HibernateException e) {
            System.err.println("Initial SessionFactory creation failed." + e);
            throw new ExceptionInInitializerError(e);
        }
        return sessionFactory;
    }
}
