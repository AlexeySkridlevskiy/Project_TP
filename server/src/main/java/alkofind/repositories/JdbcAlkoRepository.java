package alkofind.repositories;

import alkofind.AlcoholTypes;
import alkofind.Alko;
import alkofind.User;
import alkofind.Marks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class JdbcAlkoRepository implements AlkoRepository {

    // Spring Boot will create and configure DataSource and JdbcTemplate
    // To use it, just @Autowired
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int countAlko() {
        return jdbcTemplate
                .queryForObject("select count(*) from Alcohol;", Integer.class);
    }
    @Override
    public int countUsers() {
        return jdbcTemplate
                .queryForObject("select count(*) from Users;", Integer.class);
    }
    @Override
    public int countMark(Long id) {
        return jdbcTemplate
                .queryForObject("SELECT AVG(Mark) FROM Marks WHERE AlcoholID = ?;",new Object[]{id},Integer.class);
    }

    @Override
    public int save(Alko alko) {
        return jdbcTemplate.update(
                "insert into Alcohol (ID, Name, TypeID, Creator, Price) values(?,?,?,?,?)",
                alko.getID(), alko.getName(), alko.getTypeID(), alko.getCreator(),alko.getPrice());
    }
    @Override
    public int save(Marks mark) {
        return jdbcTemplate.update(
                "insert into Marks (Mark, UserID, AlcoholID) values(?,?,?)",
                mark.getMark(), mark.getUserID(), mark.getAlcoholID()
        );
    }
    @Override
    public int save(AlcoholTypes alcoholTypes) {
        return jdbcTemplate.update(
                "insert into AlcoholTypes (ID, Type) values(?,?)",
                alcoholTypes.getID(), alcoholTypes.getType()
        );
    }
    @Override
    public int save(User user) {
        return jdbcTemplate.update(
                "insert into Users (ID, Name, Surname, Age, Email, Password) values(?,?,?,?,?,?)",
                user.getID(), user.getName(), user.getSurname(),user.getAge(),user.getEmail(),user.getPassword()
        );
    }



    @Override
    public int deleteByIdAlko(Long id) {
        return jdbcTemplate.update(
                "delete Alcohol where id = ?",
                id);
    }
    @Override
    public int deleteMark(Long aID,Long uID) {
        return jdbcTemplate.update(
                "DELETE\n" +
                        "FROM Marks\n" +
                        "WHERE Marks.AlcoholID = ? AND Marks.UserID = ? ",
                aID,uID);
    }
    @Override
    public int deleteByIdUser(Long id) {
        return jdbcTemplate.update(
                "delete Users where id = ?",
                id);
    }

    @Override
    public Optional<Alko> findByNameAlko(String name) {
        return jdbcTemplate.queryForObject(
                "select * from Alcohol where name = ?",
                new Object[]{name},
                (rs, rowNum) ->
                        Optional.of(new Alko(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("company"),
                                rs.getInt("type"),
                                rs.getDouble("price")
                        ))
        );
    }
    @Override
    public Optional<Marks> showMarks(Long aID, Long uID){
        return jdbcTemplate.queryForObject(
                "SELECT * " +
                        "FROM Marks " +
                        "WHERE Marks.AlcoholID = ? AND Marks.UserID = ? ",
                new Object[]{aID,uID},
                (rs, rowNum) ->
                        Optional.of(new Marks(
                                rs.getInt("Mark"),
                                rs.getInt("UserID"),
                                rs.getInt("AlcoholID")
                        ))
        );
    }
    @Override
    public Optional<Alko> showTop(){
        return jdbcTemplate.queryForObject(
                "SELECT *, AVG(Marks.Mark) as Rating " +
                        "FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID JOIN Marks ON Alcohol.ID = Marks.AlcoholID " +
                        "GROUP BY Alcohol.ID " +
                        "ORDER BY Rating DESC;",
                new Object[]{},
                (rs, rowNum) ->
                        Optional.of(new Alko(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("company"),
                                rs.getInt("type"),
                                rs.getDouble("price")
                        ))
        );
    }
    @Override
    public Optional<Alko> findByKindAlko(int TypeID) {
        return jdbcTemplate.queryForObject(
                "select * from Alcohol where TypeID = ?",
                new Object[]{TypeID},
                (rs, rowNum) ->
                        Optional.of(new Alko(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("company"),
                                rs.getInt("type"),
                                rs.getDouble("price")
                        ))
        );
    }
    @Override
    public Optional<Alko> findByIDAlko(int ID) {
        return jdbcTemplate.queryForObject(
                "select * from Alcohol where ID = ?",
                new Object[]{ID},
                (rs, rowNum) ->
                        Optional.of(new Alko(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("company"),
                                rs.getInt("type"),
                                rs.getDouble("price")
                        ))
        );
    }
    @Override
    public Optional<Alko> findByTypeName(String name) {
        return jdbcTemplate.queryForObject(
                "SELECT * " +
                        "FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID " +
                        "WHERE AlcoholTypes.Type LIKE ?;",
                new Object[]{"%" + name + "%"},
                (rs, rowNum) ->
                        Optional.of(new Alko(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("company"),
                                rs.getInt("type"),
                                rs.getDouble("price")
                        ))
        );
    }
    @Override
    public List<Alko> findAll() {
        return jdbcTemplate.query(
                "select * from Alcohol",
                (rs, rowNum) ->
                        new Alko(
                                rs.getLong("id"),
                                rs.getString("name"),
                                rs.getString("company"),
                                rs.getInt("type"),
                                rs.getDouble("price")
                        )
        );
    }
}