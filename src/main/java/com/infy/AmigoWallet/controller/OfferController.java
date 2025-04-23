package com.infy.AmigoWallet.controller;

import com.infy.AmigoWallet.model.Offer;
import com.infy.AmigoWallet.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/offer")
public class OfferController {

    private OfferService offerService;

    @Autowired
    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOffer(@RequestBody Offer offer) {
        offerService.addOffer(offer);
    }

    @GetMapping
    public List<Offer> findAllOffers() {
        return offerService.findAll();
    }
}
