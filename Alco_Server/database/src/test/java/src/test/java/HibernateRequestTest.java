package src.test.java;

import database.Dao;
import database.HibernateRequest;
import database.User;
import database.UserDao;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

class HibernateRequestTest {

    private static final Flyway flyway = new Flyway();
    private static HibernateRequest hibernateRequest;

    @BeforeAll
    static void init()
    {
        try {
            String url = "jdbc:mysql://localhost/server";
            String username = "root";
            String password = "##@skrid2002@##";
            flyway.setDataSource(url, username, password);
            flyway.repair();
            flyway.migrate();
            hibernateRequest = new HibernateRequest();
        } catch (Exception e ) {
            e.printStackTrace();
        }
    }

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

    @AfterAll
    static void clean() {
        flyway.clean();
    }
}