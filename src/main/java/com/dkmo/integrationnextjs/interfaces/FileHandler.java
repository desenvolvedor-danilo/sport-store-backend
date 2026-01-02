package com.dkmo.integrationnextjs.interfaces;


import org.springframework.web.multipart.MultipartFile;

public interface FileHandler {
    public String saveFile(MultipartFile file);
}
