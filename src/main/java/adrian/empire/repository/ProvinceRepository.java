package adrian.empire.repository;

import adrian.empire.model.Province;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.util.List;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ProvinceRepository implements PanacheRepository<Province> {

    @Transactional
    public void createProvinceWithSettlement(Province province) {
        persist(province); // Will cascade and persist settlements
    }

    public List<Province> getAllProvinces() {
        return findAll().list();
    }
}
