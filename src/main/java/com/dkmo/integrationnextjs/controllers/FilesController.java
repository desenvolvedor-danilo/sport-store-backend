package com.dkmo.integrationnextjs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dkmo.integrationnextjs.services.EditPictureProfile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/photos",produces = {"application/json"})
public class FilesController {
    
    @Autowired
    private EditPictureProfile editPictureProfile;
    
    @PostMapping("/save-picture")
    public String saveFile(@RequestParam("file")MultipartFile file,@RequestParam("email")String email){
        return editPictureProfile.editPicture(file, email);
    }
    
}
