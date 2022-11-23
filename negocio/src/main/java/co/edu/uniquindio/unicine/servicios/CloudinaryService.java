package co.edu.uniquindio.unicine.servicios;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryService {

    private Cloudinary cloudinary;
    private Map<String, String> config;

    public CloudinaryService() {
        config = new HashMap<>();
        config.put("cloud_name", "djrslfkmr");
        config.put("api_key", "928966742378797");
        config.put("api_secret", "hqxKTuqBQKOv3-yiMFJBTcH6-aU");
        cloudinary = new Cloudinary(config);
    }

    public Map subirImagen(File file, String carpeta) throws Exception {
        Map respuesta = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", String.format("unicine/%s", carpeta)));
        return respuesta;
    }

    public Map eliminarImagen(String idImagen) throws Exception {
        Map respuesta = cloudinary.uploader().destroy(idImagen, ObjectUtils.emptyMap());
        return respuesta;
    }
}
