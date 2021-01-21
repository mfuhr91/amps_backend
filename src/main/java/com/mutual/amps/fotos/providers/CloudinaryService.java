package com.mutual.amps.fotos.providers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CloudinaryService {

    Cloudinary cloudinary;

    private Map<String, String> valuesMap = new HashMap<>();

    public CloudinaryService() {
        valuesMap.put("cloud_name", "mfuhr91");
        valuesMap.put("api_key", "552315511962245");
        valuesMap.put("api_secret", "5yw3wnPTVa-JiWC1mBAx2oxB5fQ");
        cloudinary = new Cloudinary(valuesMap);
    }
    public Map upload(MultipartFile multipartFile, String tipo) throws IOException {

        File file = convert(multipartFile);
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
        Map result;
        
        if(tipo.contains("socio")){    
            result = cloudinary.uploader().unsignedUpload(file, "amps_socios", ObjectUtils.asMap( "public_id", String.valueOf( sdf.format( new Date() ) ) ) );     
        } else {    
            result = cloudinary.uploader().unsignedUpload(file, "amps_comercios" , ObjectUtils.asMap( "public_id", String.valueOf( sdf.format( new Date() ) ) ) );  
        }                                                                       
                                      
        file.delete();
        return result;
    }

    public Map delete(String id) throws IOException {        
        Map result = cloudinary.uploader().destroy(id, ObjectUtils.emptyMap());
        return result;
    }

    private File convert(MultipartFile multipartFile) throws IOException {
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fo = new FileOutputStream(file);
        fo.write(multipartFile.getBytes());
        fo.close();
        return file;
    }
}
