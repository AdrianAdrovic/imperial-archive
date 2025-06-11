package adrian.empire.model;

import org.jboss.resteasy.reactive.PartType;
import jakarta.ws.rs.FormParam;
import java.io.File;

public class FormData {

    @FormParam("file")
    @PartType("application/octet-stream")
    public File file;

    @FormParam("filename")
    @PartType("text/plain")
    public String fileName;
}
