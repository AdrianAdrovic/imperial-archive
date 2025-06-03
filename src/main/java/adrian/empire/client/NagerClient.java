package adrian.empire.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import java.util.List;

@RegisterRestClient(configKey = "nager-api")
@Path("/api/v3")
@Produces(MediaType.APPLICATION_JSON)
public interface NagerClient {

    @GET
    @Path("/NextPublicHolidays/{countryCode}")
    List<HolidayResponse> getHolidays(@PathParam("countryCode") String countryCode);
}
