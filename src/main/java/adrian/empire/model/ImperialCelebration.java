package adrian.empire.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ImperialCelebration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long celebration_id;

    public String name;
    public String local_name;
    public LocalDate date;
    public String country_code;
    public String era;

    @ManyToMany
    @JoinTable(
        name = "celebration_settlement",
        joinColumns = @JoinColumn(name = "celebration_id"),
        inverseJoinColumns = @JoinColumn(name = "settlement_id")
    )
    public List<Settlement> settlements;
}
