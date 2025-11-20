package com.dkmo.integrationnextjs.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.interfaces.IHandlerFiles;
import com.dkmo.integrationnextjs.models.Logins;
import com.dkmo.integrationnextjs.repository.LoginsRepository;
@Service
public class PictureProfileService {
@Autowired
private LoginsRepository loginsRepository;
public String savePictureProfile(String email,MultipartFile file){
    Logins logins = loginsRepository.findByEmail(email);
    IHandlerFiles caminho = new IHandlerFiles() {
        @Override 
        public String saveFile(MultipartFile arquivo){
            var path = "src/main/resources/static/files/profiles/";
        try {
            if(!arquivo.isEmpty()){
            byte [] imagem = arquivo.getBytes();
            Path fullPath = Paths.get(path+arquivo.getOriginalFilename());
            Files.write(fullPath, imagem);                                                                                                                                                      
            return "http://localhost:8080/files/profiles/"+arquivo.getOriginalFilename();
            }
            } catch (Exception e) {
            System.out.println(e.getMessage());
            }
            return null;
        }
    };
    logins.setFotoPerfil(caminho.saveFile(file));
    loginsRepository.save(logins);
    return "ok";
}
}
