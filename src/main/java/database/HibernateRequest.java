package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.intellij.lang.annotations.Language;

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

    public User getUserByID(long UserID) {
        List<User> result = select("from User where ID = '"+UserID+"'");
        return result.isEmpty() ? null : result.get(0);
    }

    public Alcohol getAlcoholByID(long AlcoholID) {
        List<Alcohol> result = select("from Alcohol where ID = '"+AlcoholID+"'");
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Alcohol> getNameAlcoholByType(long typeID) {
        List<Alcohol> result = select("from Alcohol where TypeID = '" + typeID + "'");
        return result;
    }

    public List<Alcohol> getSearchAlcoholByName(String nameAlcohol)
    {
        List<Alcohol> result = select("from Alcohol where Name LIKE '%"+nameAlcohol+"%'");
        return result;
    }

    public List<AlcoholTypes> getAllAlcoholTypes()
    {
        List<AlcoholTypes> result = select("select Type from AlcoholTypes");
        return result;
    }

    public String getTypeByID(long ID)
    {
        List<AlcoholTypes> result = select("from AlcoholTypes where ID = " + ID + "");
        return result.isEmpty() ? null : result.get(0).getType();
    }

    public double getRating(long alcoholID)
    {
        List<Marks> result = select("from Marks where AlcoholID = '"+alcoholID+"'");
        double rating = 0;
        if (result.isEmpty()){
            return 0;
        }
        for (var mark : result ){
            rating += Integer.parseInt(mark.getMark());
        }
        rating /= result.size();
        return  rating;
    }
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