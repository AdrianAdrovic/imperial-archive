package adrian.empire.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long province_id;

    public String name;
    public String capital_city;
    public String governor_name;
    public String status; // e.g., Loyal, Rebellious
    public String era; // e.g., 3E 427

    @OneToMany(mappedBy = "province")
    public List<Settlement> settlements;

    @OneToMany(mappedBy = "province")
    public List<ImperialEdict> edicts;
}
