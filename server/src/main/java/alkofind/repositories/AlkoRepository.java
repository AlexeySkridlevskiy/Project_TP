package alkofind.repositories;

import alkofind.AlcoholTypes;
import alkofind.Alko;
import alkofind.User;
import alkofind.Marks;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AlkoRepository {

    int countAlko();
    int countUsers();
    int countMark(Long id);

    int save(Alko alko);  //insert
    int save(User user);
    int save(Marks mark);
    int save(AlcoholTypes alcoholTypes);

    int deleteByIdAlko(Long id);
    int deleteByIdUser(Long id);
    int deleteMark(Long aID,Long uID);


    List<Alko> findAll();

    Optional<Alko> findByNameAlko(String name);
    Optional<Alko> findByKindAlko(int kind);
    Optional<Alko> findByIDAlko(int id);
    Optional<Alko> findByTypeName(String name);
    Optional<Alko> showTop();
    Optional<Marks>showMarks(Long aID, Long uID);


}
