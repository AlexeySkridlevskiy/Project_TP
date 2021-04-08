package src.test.java;

import database.Dao;
import database.HibernateRequest;
import database.User;
import database.UserDao;
import database.Alcohol;
import database.AlcoholDao;
import database.Marks;
import database.MarksDao;
import database.AlcoholTypes;
import database.AlcoholTypesDao;

import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;

class HibernateRequestTest {

    @Test
    public void requestTest() {
        Dao<User> userDao = new UserDao();
        List<User> users;
        try {
            users = HibernateRequest.getAllTable();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        for (var w : users)
            userDao.save(w);
        for (var w : userDao.getAll())
            System.out.println(w.getName());
    }

//    @Test
//    public void getNameAlco() {
//        Dao<Alcohol> alcoholDao = new AlcoholDao();
//        List<Alcohol> alcohol;
//        try {
//            alcohol = HibernateRequest.getNameAlcoByType();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return;
//        }
//        for (var w : alcohol)
//            alcoholDao.save(w);
//        for (var w : alcoholDao.getAll())
//            System.out.println(w.getName());
//    }
}