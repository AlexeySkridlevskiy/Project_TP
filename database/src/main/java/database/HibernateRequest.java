package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.intellij.lang.annotations.Language;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HibernateRequest {

    SessionFactory sessionFactory;

    public HibernateRequest() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    private List select(@Language("HQL") String queryString) {
        Session session = sessionFactory.openSession();
        Transaction tr = session.beginTransaction();
        Query query = session.createQuery(queryString);
        List result = query.getResultList();
        tr.commit();
        return result;
    }

    public List<User> getAllTableUser() {
        List<User> result = select("from User where ID = 1");
        return result;
    }

    public List<Alcohol> getNameAlcoByType(String typeID)
    {
        List<Alcohol> result = select("from Alcohol where TypeID = "+typeID+"");
        return result;
    }

    public List<AlcoholTypes> getAllTypesAlco()
    {
        List<AlcoholTypes> result = select("from AlcoholTypes");
        return result;
    }

    public List<Marks> getMarks()
    {
        List<Marks> result = select("from Marks where UserID = 1");
        return result;
    }

//    public static List<Alcohol> getNameAlcoByType() throws SQLException {
//        List<Alcohol> alcohol = new ArrayList<>();
//
//        try {
//            Query<Alcohol> query = session.createQuery("from database.Alcohol group by ID", Alcohol.class);
//            alcohol = query.list();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        HibernateUtil.shutdown();
//        return alcohol;
//    }

}


class HibernateUtil {
    private static SessionFactory sessionFactory;

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(); //"hibernate.cfg.xml"
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().configure();
        sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}