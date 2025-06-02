package adrian.empire.resource;

import adrian.empire.model.Province;
import adrian.empire.repository.ProvinceRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/provinces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProvinceResource {

    @Inject
    ProvinceRepository provinceRepository;

    @POST
    public void createProvince(Province province) {
        provinceRepository.createProvinceWithSettlement(province);
    }

    @GET
    public List<Province> getAllProvinces() {
        return provinceRepository.getAllProvinces();
    }
}
