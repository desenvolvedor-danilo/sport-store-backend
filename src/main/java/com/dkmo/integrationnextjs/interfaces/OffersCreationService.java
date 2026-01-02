package com.dkmo.integrationnextjs.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import com.dkmo.integrationnextjs.models.Offers;

public interface OffersCreationService{
    public ResponseEntity<String> createOffers(Offers deal,MultipartFile [] images);
}
