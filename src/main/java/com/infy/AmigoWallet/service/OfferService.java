package com.infy.AmigoWallet.service;

import com.infy.AmigoWallet.model.Offer;
import com.infy.AmigoWallet.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private OfferRepository offerRepository;

    @Autowired
    public OfferService(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    public void addOffer(Offer offer) {
        offerRepository.saveAndFlush(offer);
    }

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }
}
