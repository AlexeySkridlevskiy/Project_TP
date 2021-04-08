package database;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name = "Marks")
public class Marks {

    @Getter
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long ID;

    @Getter
    @Setter
    @Column(name = "Mark", length = 15, nullable = false)
    private String Mark;

    @Getter
    @Setter
    @Column(name = "UserID", length = 20, nullable = false)
    private Long UserID;

    @Getter
    @Setter
    @Column(name = "AlcoholID", length = 15, nullable = false)
    private String AlcoholID;
}