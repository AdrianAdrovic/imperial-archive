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
    public String status;
    public String era;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Settlement> settlements;

    @OneToMany(mappedBy = "province", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<ImperialEdict> edicts;

    // New field for file upload
    public String file_path;
}
