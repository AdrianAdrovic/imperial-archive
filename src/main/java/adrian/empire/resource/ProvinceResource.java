package adrian.empire.resource;

import adrian.empire.model.FormData;
import adrian.empire.model.Province;
import adrian.empire.repository.ProvinceRepository;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.jboss.resteasy.reactive.MultipartForm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Path("/provinces")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProvinceResource {

    @Inject
    ProvinceRepository provinceRepository;

    @GET
    public List<Province> getAllProvinces() {
        return provinceRepository.listAll();
    }

    @GET
    @Path("/{id}")
    public Response getProvinceById(@PathParam("id") Long id) {
        Province province = provinceRepository.findById(id);
        if (province == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(province).build();
    }

    // ✅ NOW TRANSACTIONAL
    @POST
    @Transactional
    public Response createProvince(Province province) {
        try {
            System.out.println("▶️ Creating province: " + province.name);
            provinceRepository.persist(province);
            System.out.println("✅ Province saved.");
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("❌ Error while saving province: " + e.getMessage())
                    .build();
        }
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Transactional
    public Response uploadFile(@QueryParam("id") Long provinceId,
            @MultipartForm FormData formData) {

        Province province = provinceRepository.findById(provinceId);
        if (province == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Province not found").build();
        }

        try {
            String folder = "uploads/";
            File uploadDir = new File(folder);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = folder + formData.fileName;
            File savedFile = new File(filePath);
            Files.copy(formData.file.toPath(), savedFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            province.file_path = filePath;
            provinceRepository.persist(province);

            return Response.ok("File uploaded and path saved").build();

        } catch (IOException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to save file").build();
        }
    }
}
