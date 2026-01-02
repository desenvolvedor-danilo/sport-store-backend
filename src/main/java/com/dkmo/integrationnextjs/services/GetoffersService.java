package com.dkmo.integrationnextjs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dkmo.integrationnextjs.dto.ResponseOfferDto;
import com.dkmo.integrationnextjs.interfaces.OffersProvider;
import com.dkmo.integrationnextjs.models.Offers;
import com.dkmo.integrationnextjs.repository.DealsRepository;

@Service
public class GetoffersService  implements OffersProvider{
   @Autowired
   private DealsRepository dealsRepository;
    @Override
    public List<Offers> getAllDeals() {
        List<Offers> deals = dealsRepository.findAll();
        return deals;
    }
    @Override
    public ResponseOfferDto searchCodigo(Long codigo) {
       Offers deal = dealsRepository.findByCodigo(codigo);
       ResponseOfferDto responseDealDto = new ResponseOfferDto(deal.getTitulo(), deal.getNome(),deal.getCaminho().replaceAll(",$", "").split(","), deal.getValor());
       return responseDealDto;
    }
}
