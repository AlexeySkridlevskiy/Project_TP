package database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "User")
public class User {

    @Getter
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long ID;

    @Getter
    @Setter
    @Column(name = "Name", length = 15, nullable = false)
    private String Name;

    @Getter
    @Setter
    @Column(name = "Surname", length = 25, nullable = false)
    private String Surname;

    @Getter
    @Setter
    @Column(name = "Age", length = 3)
    private int Age;

    @Getter
    @Setter
    @Column(name = "Email", length = 50, nullable = false)
    private String Email;

    @Getter
    @Setter
    @Column(name = "Password", length = 50, nullable = false)
    private String Password;
}