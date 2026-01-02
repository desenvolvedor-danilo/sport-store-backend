package com.dkmo.integrationnextjs.controllers;

import java.util.List;  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.dkmo.integrationnextjs.dto.ResponseOfferDto;
import com.dkmo.integrationnextjs.models.Offers;
import com.dkmo.integrationnextjs.services.GetoffersService;
import com.dkmo.integrationnextjs.services.OfferInsertService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/offers")
public class OffersController {
    
    @Autowired
    private OfferInsertService insertofferService;
    
    @Autowired
    private GetoffersService getoffersService;
    
    @PostMapping("/create")
    public ResponseEntity<String> createoffer(@RequestParam(name="titulo") String titulo,@RequestParam(name="nome") String nome,@RequestParam(name="valor") String valor, @RequestParam(name="imagens") MultipartFile [] imagens){
    Offers offer = new Offers();
    offer.setTitulo(titulo);
    offer.setNome(nome);
    offer.setValor(valor);
    return insertofferService.createOffers(offer,imagens);
    }

    @GetMapping("/findall")
    public List<Offers> getAlloffers(){
    return getoffersService.getAllDeals();
    }

    @GetMapping("/findbycodigo")
    public ResponseOfferDto getByCodigo(@RequestParam(name = "codigo")Long codigo){
    return getoffersService.searchCodigo(codigo);
    }

}
