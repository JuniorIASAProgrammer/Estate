package com.estatemarket.realestate.service;

import com.estatemarket.realestate.api.enums.DealStatusEnum;
import com.estatemarket.realestate.repo.DealRepo;
import com.estatemarket.realestate.repo.EstateRepo;
import com.estatemarket.realestate.repo.OfferRepo;
import com.estatemarket.realestate.repo.UserRepo;
import com.estatemarket.realestate.repo.model.Deal;
import com.estatemarket.realestate.repo.model.Estate;
import com.estatemarket.realestate.repo.model.Offer;

import com.estatemarket.realestate.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DealService {

    private final DealRepo dealRepo;
    private final UserRepo userRepo;
    private final OfferRepo offerRepo;
    private final EstateRepo estateRepo;

    public List<Offer> fetchByEstateId(long estateId) {
        Estate estate = estateRepo.findById(estateId).orElseThrow(IllegalArgumentException::new);
        return offerRepo.findAllByEstate(estate);
    }

    public List<Deal> fetchByRealtorId() {
         String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
         User realtor = userRepo.findByEmail(currentUserEmail);
         return dealRepo.findAllByRealtor(realtor);
    }

    public long createDeal(long offerId) {
        Offer selectedOffer = offerRepo.getById(offerId);
        Estate offerEstate = selectedOffer.getEstate();
        User offerRealtor = selectedOffer.getRealtor();
        Deal deal = new Deal(offerEstate, offerRealtor);
        Deal savedDeal = dealRepo.save(deal);
        return savedDeal.getId();
    }

    public void closeDeal(long dealId) {
        Deal dealToClose = dealRepo.findById(dealId).orElseThrow(IllegalArgumentException::new);
        if (dealToClose.getStatus() == DealStatusEnum.STRIKED) {
            dealToClose.setStatus(DealStatusEnum.COMPLETED);
        }
        dealRepo.save(dealToClose);
    }

    public void cancelDeal(long dealId) {
        Deal dealToCancel = dealRepo.findById(dealId).orElseThrow(IllegalArgumentException::new);
        if (dealToCancel.getStatus() == DealStatusEnum.STRIKED) {
            dealToCancel.setStatus(DealStatusEnum.REVOKED);
        }
        dealRepo.save(dealToCancel);
    }

    public long createOffer(long estateId) {
        Estate estate = estateRepo.getById(estateId);
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User realtor = userRepo.findByEmail(currentUserEmail);
        Offer newOffer = new Offer(estate, realtor);
        offerRepo.save(newOffer);
        return newOffer.getId();
    }

    public void deleteOffer(long estateId) {
        Estate estate = estateRepo.findById(estateId).orElseThrow(IllegalArgumentException::new);
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User realtor = userRepo.findByEmail(currentUserEmail);
        offerRepo.deleteOfferByEstateAndRealtor(estate, realtor);
    }
}
