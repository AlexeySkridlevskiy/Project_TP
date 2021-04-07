package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateRequest {

    private static final Session session = HibernateUtil.getSessionFactory().openSession();

    public static List<User> getAllTable() throws SQLException {
        List<User> users = new ArrayList<>();

        try {
            Query<User> query = session.createQuery("from database.User group by id", User.class);
            users = query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HibernateUtil.shutdown();
        return users;
    }
}

class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}