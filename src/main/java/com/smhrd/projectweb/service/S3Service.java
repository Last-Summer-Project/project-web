package com.smhrd.projectweb.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.util.IOUtils;
import com.smhrd.projectweb.exception.S3KeyDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private ResourceLoader resourceLoader;
    private AmazonS3 amazonS3;

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
        final WritableResource writableResource = (WritableResource) this.resourceLoader
                .getResource(String.format("s3://%s/%s", bucketName, key));

        try (OutputStream outputStream = writableResource.getOutputStream()) {
            IOUtils.copy(resource.getInputStream(), outputStream);
        }

        return key;
    }

    public void removeObject(String bucketName, String key) throws S3KeyDoesNotExistException {
        if (amazonS3.doesObjectExist(bucketName, key)) {
            amazonS3.deleteObject(bucketName, key);
        } else {
            throw new S3KeyDoesNotExistException(bucketName, key);
        }
    }
}
