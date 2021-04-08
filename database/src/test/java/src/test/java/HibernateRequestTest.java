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

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRequestTest {

    private static HibernateRequest hibernateRequest = new HibernateRequest();

    @Test
    void UserTest() {
        Dao<User> userDao = new UserDao();

        System.out.println("User: ");
        var result = hibernateRequest.getAllTableUser();
        for (var w : result)
            userDao.save(w);
        for (var w : userDao.getAll()) {
            System.out.println(w.getName());
            System.out.println(w.getSurname());
            System.out.println(w.getAge());
            System.out.println(w.getEmail());
        }
    }

    @Test
    void AlcoholTest()
    {
        Dao<Alcohol> alcoholDao = new AlcoholDao();

        var result = hibernateRequest.getNameAlcoByType("1");

        System.out.println("\nName alcohol by TypeID: ");
        for (var w : result)
            alcoholDao.save(w);
        for (var w : alcoholDao.getAll()) {
            System.out.println(w.getName());
            System.out.println(w.getCreator());
            System.out.println(w.getPrice());
        }
    }

    @Test
    void AlcoTypeTest()
    {
        Dao<AlcoholTypes> alcoholTypesDao = new AlcoholTypesDao();
        var result = hibernateRequest.getAllTypesAlco();

        System.out.println("\nAlcohol types: ");
        for (var w : result)
            alcoholTypesDao.save(w);
        for (var w : alcoholTypesDao.getAll())
            System.out.println(w.getType());
    }

    @Test
    void MarksTest()
    {
        Dao<Marks> marksDao = new MarksDao();
        var result = hibernateRequest.getMarks();

        System.out.println("\nMarks by User 1: ");
        for (var w : result)
            marksDao.save(w);
        for (var w : marksDao.getAll()) {
            System.out.println(w.getMark());
        }
    }
}