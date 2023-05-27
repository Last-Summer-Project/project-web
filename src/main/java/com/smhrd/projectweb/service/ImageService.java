package com.smhrd.projectweb.service;

import com.smhrd.projectweb.entity.sql.Image;
import com.smhrd.projectweb.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    private final ImageMapper imageMapper;
    private final S3Service s3Service;

    private static final String BASE64SEP = ";base64,";

    public Long uploadImageFromBase64(String name, String base64) {
        if (name == null || name.isEmpty() || base64 == null || base64.isEmpty()) return null;
        // Is data url?
        String ext = null;
        if (base64.startsWith("data:image/")) {
            // Check it is base64.
            if (!base64.contains(BASE64SEP)) return null;
            ext = base64.substring(base64.indexOf("data:image/"), base64.indexOf(BASE64SEP));
            base64 = base64.substring(base64.indexOf(BASE64SEP) + BASE64SEP.length());
        }

        if (ext == null && name.contains(".")) {
            ext = name.substring(name.lastIndexOf("."));
        } else {
            ext = "jpg";
        }

        String key;
        try {
            byte[] data = Base64.getDecoder().decode(base64);
            key = s3Service.putObject(data, name + "." + ext);
            log.error(key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return null;
        }
        if (key == null) return null;

        // Set image
        Image img = new Image();
        img.setName(name);
        img.setUrl(key);

        Long logInserted = imageMapper.insert(img);
        return logInserted != 1 ? null : img.getId();
    }
}
