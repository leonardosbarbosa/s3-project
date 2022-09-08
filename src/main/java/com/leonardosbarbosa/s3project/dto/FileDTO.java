package com.leonardosbarbosa.s3project.dto;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class FileDTO implements Serializable {
    private MultipartFile file;

    public FileDTO() {
    }

    public FileDTO(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
