package com.dkmo.integrationnextjs.interfaces;
import java.util.List;

import com.dkmo.integrationnextjs.dto.ResponseOfferDto;
import com.dkmo.integrationnextjs.models.Offers;

public interface OffersProvider {
    public List<Offers> getAllDeals();
    public ResponseOfferDto searchCodigo(Long codigo);

}
