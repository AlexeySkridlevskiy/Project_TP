package database;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Alcohol")
public class Alcohol {

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
    @Column(name = "TypeID")
    private Long TypeID;

    @Getter
    @Setter
    @Column(name = "Creator", length = 50, nullable = false)
    private String Creator;

    @Getter
    @Setter
    @Column(name = "Price", length = 10, nullable = false)
    private String Price;
}