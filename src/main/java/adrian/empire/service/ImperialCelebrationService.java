package adrian.empire.service;

import adrian.empire.client.NagerClient;
import adrian.empire.client.HolidayResponse;
import adrian.empire.model.ImperialCelebration;
import adrian.empire.model.Settlement;
import adrian.empire.repository.ImperialCelebrationRepository;
import adrian.empire.repository.SettlementRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class ImperialCelebrationService {

    @Inject
    @RestClient
    NagerClient nagerClient;

    @Inject
    ImperialCelebrationRepository celebrationRepository;

    @Inject
    SettlementRepository settlementRepository;

    @Transactional
    public void fetchAndStoreCelebrations(String countryCode, String era) {
        List<HolidayResponse> holidays = nagerClient.getHolidays(countryCode);
        List<Settlement> settlements = settlementRepository.listAll();

        for (HolidayResponse h : holidays) {
            // Check if already exists
            ImperialCelebration existing = celebrationRepository.findByNameAndDate(h.name, era);
            if (existing != null) {
                continue; // Skip duplicates
            }

            // Create new celebration
            ImperialCelebration c = new ImperialCelebration();
            c.name = h.name;
            c.local_name = h.localName;
            c.date = h.date;
            c.country_code = h.countryCode;
            c.era = era;
            c.settlements = settlements; // Associate with all settlements (or you can filter)

            celebrationRepository.persist(c);
        }
    }
}
