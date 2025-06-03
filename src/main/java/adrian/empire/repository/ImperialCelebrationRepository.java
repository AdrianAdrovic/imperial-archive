package adrian.empire.repository;

import adrian.empire.model.ImperialCelebration;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class ImperialCelebrationRepository implements PanacheRepository<ImperialCelebration> {
    public ImperialCelebration findByNameAndDate(String name, String era) {
        return find("name = ?1 and era = ?2", name, era).firstResult();
    }
}
