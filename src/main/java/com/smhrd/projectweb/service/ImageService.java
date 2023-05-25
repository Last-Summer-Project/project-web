package com.smhrd.projectweb.service;

import com.smhrd.projectweb.entity.sql.Image;
import com.smhrd.projectweb.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.util.Base64;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageService {
    private final ImageMapper imageMapper;
    private final S3Service s3Service;

    public Long uploadImageFromBase64(String name, String base64) {
        if (name == null || name.isEmpty() || base64 == null || base64.isEmpty()) return null;
        // Is data url?
        String ext = null;
        if (base64.startsWith("data:image/") || base64.startsWith("image")) {
            // Check it is base64.
            if (!base64.contains(";base64,")) return null;
            base64 = base64.split(",", 1)[1];
            ext = base64.substring(base64.indexOf("data:image/"), base64.indexOf(";base64,"));
        }

        if (ext == null && name.contains(".")) {
            ext = name.substring(name.lastIndexOf("."));
        } else {
            ext = "jpg";
        }

        String key = null;
        try {
            byte[] data = Base64.getDecoder().decode(base64);
            ByteArrayResource bar = new ByteArrayResource(data);
            key = s3Service.putObject(bar, name + "." + ext);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
        if (key == null) return null;

        // Set image
        Image img = new Image();
        img.setName(name);
        img.setUrl(key);

        Long id = imageMapper.insert(img);
        return id == 0 ? null : id;
    }
}
