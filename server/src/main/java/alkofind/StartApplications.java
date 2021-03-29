package alkofind;

import alkofind.repositories.AlkoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
@SpringBootApplication
public class StartApplications implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(StartApplications.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    //@Qualifier("jdbcBookRepository")              // Test JdbcTemplate
    @Qualifier("namedParameterJdbcAlkoRepository")  // Test NamedParameterJdbcTemplate
    private AlkoRepository alkoRepository;

    public static void main(String[] args) {
        SpringApplication.run(StartApplications.class);
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("StartApplications...");
        runJDBC();
    }
    void runJDBC() {

        log.info("Creating tables for testing...");

        jdbcTemplate.execute("DROP TABLE Alcohol IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE Alcohol (" +
                "    ID INTEGER NOT NULL AUTO_INCREMENT," +
                "    Name VARCHAR(20) NOT NULL," +
                "    TypeID INTEGER NOT NULL," +
                "    Creator VARCHAR(50) NOT NULL," +
                "    Price DECIMAL(10, 2) NOT NULL," +
                "    PRIMARY KEY (ID)" +
                ")");
        jdbcTemplate.execute("DROP TABLE Marks IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE Marks (" +
                "    Mark INTEGER NOT NULL," +
                "    UserID INTEGER NOT NULL," +
                "    AlcoholID INTEGER NOT NULL," +
                "    CHECK (Mark > 0 AND Mark < 6)" +
                ")");
        jdbcTemplate.execute("DROP TABLE AlcoholTypes IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE AlcoholTypes (" +
                "    ID INTEGER NOT NULL AUTO_INCREMENT," +
                "    Type VARCHAR(30) NOT NULL," +
                "    PRIMARY KEY (ID)" +
                ")");
        jdbcTemplate.execute("DROP TABLE Users IF EXISTS");
        jdbcTemplate.execute("CREATE TABLE Users (" +
                "    ID INTEGER NOT NULL AUTO_INCREMENT," +
                "    Name VARCHAR(30) NOT NULL," +
                "    Surname VARCHAR(30) NOT NULL," +
                "    Age INTEGER NOT NULL," +
                "    Email VARCHAR(30) NOT NULL UNIQUE," +
                "    Password VARCHAR(20) NOT NULL ," +
                "    PRIMARY KEY (ID)" +
                ")");
        List<AlcoholTypes> alkoTypes=Arrays.asList(
                new AlcoholTypes(1,"Пиво"),
                new AlcoholTypes(2,"Водка"),
                new AlcoholTypes(3,"Шампанское"),
                new AlcoholTypes(4,"Вино")
        );
        List<User> users=Arrays.asList(
                new User((long) 1,"Егор","Яхимович",18,"zosh@gmail.com","1234"),
                new User((long) 2,"Алексейй","Мироевский",19,"vodkamoyazhiznh@gmail.com","1234"),
                new User((long) 3,"Алексей","Скридлевский",18,"lublupivo@gmail.com","1234"),
                new User((long) 4,"Екатерина","Ермолаева",19,"lubluvino@gmail.com","1234")
        );
        List<Alko> alkos=Arrays.asList(
                new Alko((long) 1,"Балтика","Baltic Beverages Holding",1, (double) 123),
                new Alko((long) 2,"Сваяк","Минский завод виноградных вин",2, (double) 12),
                new Alko((long) 3,"Советское","Минский завод виноградных вин",3, (double) 3),
                new Alko((long) 4,"Краймс","19 Crimes",4, (double) 345)
        );
        List<Marks> marks=Arrays.asList(
                new Marks(1,2,2),
                new Marks(4,3,4),
                new Marks(5,1,2),
                new Marks(2,1,1)
        );
        log.info("[SAVE ALKO]");
        alkos.forEach(alko -> {
            log.info("Saving...{}", alko.getName());
            alkoRepository.save(alko);
        });
        log.info("[SAVE USER]");
        users.forEach(user -> {
            log.info("Saving...{}", user.getName()," ",user.getSurname());
            alkoRepository.save(user);
        });
        log.info("[SAVE ALKOTYPE]");
        alkoTypes.forEach(alkoType -> {
            log.info("Saving...{}", alkoType.getType());
            alkoRepository.save(alkoType);
        });
        log.info("[SAVE MARKS]");
        marks.forEach(mark -> {
            log.info("Saving...{}");
            alkoRepository.save(mark);
        });



    }
}