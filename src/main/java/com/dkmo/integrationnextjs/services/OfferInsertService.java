package com.dkmo.integrationnextjs.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.dkmo.integrationnextjs.interfaces.OffersCreationService;
import com.dkmo.integrationnextjs.models.Offers;
import com.dkmo.integrationnextjs.models.Slides;
import com.dkmo.integrationnextjs.repository.DealsRepository;
import com.dkmo.integrationnextjs.utils.ProductsFilesSave;


@Service
public class OfferInsertService implements OffersCreationService {
    @Autowired
    private DealsRepository dealsRepository;
    ProductsFilesSave productsFilesSave= new ProductsFilesSave();
    @Override
    public ResponseEntity<String> createOffers(Offers offers,MultipartFile [] imagens) {
        StringBuilder caminho = new StringBuilder();
        for(MultipartFile imagem: imagens){
        String caminhoImagem = productsFilesSave.saveFile(imagem);
        caminho.append(caminhoImagem).append(",");
        }
        Slides slides = new Slides();
        slides.setFoto("teste");
        slides.setCodigo("1");
        offers.setSlides(slides);
        offers.setCaminho(caminho.toString());
        dealsRepository.save(offers);
        return ResponseEntity.ok().body("created with successfuly");
    }
    
}
