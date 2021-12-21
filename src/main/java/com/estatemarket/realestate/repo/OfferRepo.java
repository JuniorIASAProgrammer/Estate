package com.estatemarket.realestate.repo;

import com.estatemarket.realestate.repo.model.Estate;
import com.estatemarket.realestate.repo.model.Offer;
import com.estatemarket.realestate.repo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepo extends JpaRepository<Offer, Long> {
    List<Offer> findAllByEstate(Estate estate);
    List<Offer> deleteOfferByEstateAndRealtor(Estate estate, User realtor);
}
