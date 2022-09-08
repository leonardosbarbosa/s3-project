package com.leonardosbarbosa.s3project.services;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDateTime;

@Service
public class S3Service {
    private static final Logger LOG = LoggerFactory.getLogger(S3Service.class);

    @Autowired
    private AmazonS3 s3client;

    @Value("${s3.bucket}")
    private String bucketName;

    public URL uploadFile(MultipartFile file) {
        try {
            String originalFileName = file.getOriginalFilename();
            String fileExtension = FilenameUtils.getExtension(originalFileName);
            String s3FileName = Instant.now().getEpochSecond() + "." + fileExtension;
            InputStream is = file.getInputStream();
            String contentType = file.getContentType();
            return uploadFile(is, s3FileName, contentType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private URL uploadFile(InputStream is, String s3FileName, String contentType) {
        LOG.info("Upload start");
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentType(contentType);
        s3client.putObject(bucketName, s3FileName, is, metadata);
        LOG.info("Upload end");
        return s3client.getUrl(bucketName, s3FileName);
    }
}
