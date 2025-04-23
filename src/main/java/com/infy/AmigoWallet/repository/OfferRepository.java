package com.infy.AmigoWallet.repository;

import com.infy.AmigoWallet.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OfferRepository extends JpaRepository<Offer,Long> {
    List<Offer> findByRegionCode(String code);
}
