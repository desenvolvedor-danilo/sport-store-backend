package com.dkmo.integrationnextjs.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dkmo.integrationnextjs.interfaces.FileHandler;
import com.dkmo.integrationnextjs.models.Slides;
import com.dkmo.integrationnextjs.repository.SlidesRepository;
import com.dkmo.integrationnextjs.utils.SlidesFilesSave;


@Service
public class SlideService {
    
    @Autowired
    private SlidesRepository slidesRepository;

    public ResponseEntity<String> insertSlide(String codigo, MultipartFile file) {
        Slides slide = new Slides();
        FileHandler handlerFiles = new SlidesFilesSave();
        slide.setFoto(handlerFiles.saveFile(file));
        slide.setCodigo(codigo);
        slidesRepository.save(slide);
        return ResponseEntity.ok().body("created successfully");
    }
}
