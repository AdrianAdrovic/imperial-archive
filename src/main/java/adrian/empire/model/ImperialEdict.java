package adrian.empire.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ImperialEdict {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long edict_id;

    public String title;
    public LocalDate date_issued;
    public String author;
    public String summary;
    public String law_type; // Military, Religious, Economic

    @ManyToOne
    @JoinColumn(name = "province_id")
    public Province province;

    @ManyToMany
    @JoinTable(name = "Edict_Settlement", joinColumns = @JoinColumn(name = "edict_id"), inverseJoinColumns = @JoinColumn(name = "settlement_id"))
    public List<Settlement> affectedSettlements;
}
