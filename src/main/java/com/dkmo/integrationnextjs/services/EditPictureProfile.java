package com.dkmo.integrationnextjs.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.interfaces.FileHandler;
import com.dkmo.integrationnextjs.models.Account;
import com.dkmo.integrationnextjs.repository.LoginsRepository;

@Service
public class EditPictureProfile {
    @Autowired
    private LoginsRepository loginsRepository;
    public String editPicture(MultipartFile file, String email) {
        FileHandler iHandlerFiles = new FileHandler() {
            @Override
            public String saveFile(MultipartFile file) {
                var path = "src/main/resources/static/files/profile/";
                try {
                    if (!file.isEmpty()) {
                        byte[] image = file.getBytes();
                        Path fullPath = Paths.get(path + file.getOriginalFilename());
                        Files.write(fullPath, image);
                        return "http://localhost:8080/files/profile/" + file.getOriginalFilename();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                return null;
            }
        };
        Account logins = loginsRepository.findByEmail(email);
        if(logins != null){
            logins.setFotoPerfil(iHandlerFiles.saveFile(file));
            loginsRepository.save(logins);
            return logins.getFotoPerfil();
        }
        return null;

    }
}
