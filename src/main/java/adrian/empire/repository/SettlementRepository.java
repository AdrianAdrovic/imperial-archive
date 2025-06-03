package adrian.empire.repository;

import adrian.empire.model.Settlement;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class SettlementRepository implements PanacheRepository<Settlement> {
}
