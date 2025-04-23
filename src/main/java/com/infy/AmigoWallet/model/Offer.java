package com.infy.AmigoWallet.model;

import jakarta.persistence.*;

@Entity
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String offerContent;

    @Column(length = 2)
    private String regionCode;


    public Offer(String offerContent, long id, String regionCode) {
        this.offerContent = offerContent;
        this.id = id;
        this.regionCode = regionCode;
    }

    public Offer(String offerContent, String regionCode) {
        this.offerContent = offerContent;
        this.regionCode = regionCode;
    }

    public Offer() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOfferContent() {
        return offerContent;
    }

    public void setOfferContent(String offerContent) {
        this.offerContent = offerContent;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
}
