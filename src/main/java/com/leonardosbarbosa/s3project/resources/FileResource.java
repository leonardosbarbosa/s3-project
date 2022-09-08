package com.leonardosbarbosa.s3project.resources;

import com.leonardosbarbosa.s3project.dto.UriDTO;
import com.leonardosbarbosa.s3project.services.FileService;
import com.leonardosbarbosa.s3project.services.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/files")
public class FileResource {

    @Autowired
    private FileService service;

    @PostMapping
    public ResponseEntity<UriDTO> uploadFile(@RequestParam("file")MultipartFile file){
        UriDTO dto = service.uploadFile(file);
        return ResponseEntity.ok(dto);
    }
}
