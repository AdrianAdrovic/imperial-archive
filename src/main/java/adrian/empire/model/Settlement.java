package adrian.empire.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Settlement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long settlement_id;

    public String name;
    public String type; // City, Fort, Village
    public int population_estimate;
    public String strategic_importance; // Low, Medium, High

    @ManyToOne
    @JoinColumn(name = "province_id")
    public Province province;

    @ManyToMany(mappedBy = "affectedSettlements")
    public List<ImperialEdict> edicts;

    @ManyToMany(mappedBy = "settlements")
    public List<ImperialCelebration> celebrations;

}
