package database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "AlcoholTypes")
public class AlcoholTypes {

    @Getter
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long ID;

    @Getter
    @Setter
    @Column(name = "Type", length = 30, nullable = false)
    private String Type;
}