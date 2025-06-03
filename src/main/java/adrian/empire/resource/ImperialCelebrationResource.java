package adrian.empire.resource;

import adrian.empire.service.ImperialCelebrationService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("/celebrations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ImperialCelebrationResource {

    @Inject
    ImperialCelebrationService celebrationService;

    @POST
    @Path("/{countryCode}/{era}")
    public void fetchAndStoreCelebrations(@PathParam("countryCode") String countryCode,
            @PathParam("era") String era) {
        celebrationService.fetchAndStoreCelebrations(countryCode, era);
    }
}
