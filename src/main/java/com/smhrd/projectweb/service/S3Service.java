package com.smhrd.projectweb.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.smhrd.projectweb.exception.S3KeyDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@PropertySource("classpath:/application.properties")
public class S3Service {
    private final ResourceLoader resourceLoader;
    private final AmazonS3 s3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucketName;

    public Resource getObject(String key) throws S3KeyDoesNotExistException {
        final Resource resource = resourceLoader.getResource(String.format("s3://%s/%s", bucketName, key));
        if (resource.exists()) {
            return resource;
        }
        throw new S3KeyDoesNotExistException(bucketName, key);
    }

    public String putObject(Resource resource) throws IOException {
        String key = resource.getFilename() != null ? resource.getFilename() : UUID.randomUUID().toString();
        return putObject(resource, key);
    }

    public String putObject(Resource resource, String key) throws IOException {
        s3Client.putObject(bucketName, key, resource.getInputStream(), new ObjectMetadata());
        return key;
    }

    public void removeObject(String bucketName, String key) throws S3KeyDoesNotExistException {
        if (s3Client.doesObjectExist(bucketName, key)) {
            s3Client.deleteObject(bucketName, key);
        } else {
            throw new S3KeyDoesNotExistException(bucketName, key);
        }
    }
}
