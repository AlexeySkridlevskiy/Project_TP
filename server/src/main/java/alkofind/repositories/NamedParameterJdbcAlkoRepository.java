package alkofind.repositories;

import alkofind.Alko;
import alkofind.User;
import alkofind.Marks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public abstract class NamedParameterJdbcAlkoRepository extends JdbcAlkoRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;



    @Override
    public Optional<Alko> findByNameAlko(String name) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from Alcohol where Name = ?",
                new MapSqlParameterSource("Name", name),
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
    public Optional<Alko> findByKindAlko(int type) {
        return namedParameterJdbcTemplate.queryForObject(
                "select * from Alcohol where TypeID = ?",
                new MapSqlParameterSource("TypeID", type),
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
        return namedParameterJdbcTemplate.queryForObject(
                "select * from Alcohol where ID = ?",
                new MapSqlParameterSource("TypeID", ID),
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
        return namedParameterJdbcTemplate.query(
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

    @Override
    public Optional<Alko> findByTypeName(String name) {
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT * " +
                        "FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID " +
                        "WHERE AlcoholTypes.Type LIKE ?;",
                new MapSqlParameterSource("Name", "%" + name + "%"),
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
    public Optional<Alko> showTop(){
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT *, AVG(Marks.Mark) as Rating " +
                        "FROM Alcohol JOIN AlcoholTypes ON Alcohol.TypeID = AlcoholTypes.ID JOIN Marks ON Alcohol.ID = Marks.AlcoholID " +
                        "GROUP BY Alcohol.ID " +
                        "ORDER BY Rating DESC;",
                new MapSqlParameterSource(),
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
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("AlcoholID", aID);
        mapSqlParameterSource.addValue("UserID", uID);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT * " +
                        "FROM Marks " +
                        "WHERE Marks.AlcoholID = ? AND Marks.UserID = ? ",
                mapSqlParameterSource,
                (rs, rowNum) ->
                        Optional.of(new Marks(
                                rs.getInt("Mark"),
                                rs.getInt("UserID"),
                                rs.getInt("AlcoholID")
                        ))
        );
    }
}