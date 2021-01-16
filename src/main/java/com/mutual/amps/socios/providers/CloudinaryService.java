package com.mutual.amps.socios.providers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.mutual.amps.socios.models.Socio;

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
    public Map upload(MultipartFile multipartFile) throws IOException {
        File file = convert(multipartFile);
        Map result = cloudinary.uploader().upload(file, ObjectUtils.asMap(
                                                                            "public_id", "mutual-amps/" + file.getName(), 
                                                                            "overwrite", true));                                                                            
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


    /* public String getUrl(MultipartFile file){

       
        String url = cloudinary.url().version(1609728147).imageTag("/mutual-amps/" + file.getOriginalFilename() + ".png");

        return url;
    } 
    */
}
