//package com.estatemarket.realestate.service;
//
//import com.estatemarket.realestate.api.dto.DealDto;
//import com.estatemarket.realestate.api.dto.EstateDto;
//import com.estatemarket.realestate.api.dto.OfferDto;
//import com.estatemarket.realestate.repo.DealRepo;
//import com.estatemarket.realestate.repo.EstateRepo;
//import com.estatemarket.realestate.repo.OfferRepo;
//import com.estatemarket.realestate.repo.UserRepo;
//import com.estatemarket.realestate.repo.model.Deal;
//import com.estatemarket.realestate.repo.model.Estate;
//import com.estatemarket.realestate.repo.model.Offer;
//
//import com.estatemarket.realestate.repo.model.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class DealService {
//
//    private final DealRepo dealRepo;
//    private final UserRepo userRepo;
//
//    public List<Offer> fetchAllByEstateId(long estateId) {
//        return dealRepo.findAllByEstateId(estateId);
//    }
//
//    public List<Deal> fetchAllByRealtorId(long id) {
//        return dealRepo.findAllByRealtorId(id);
//    }
//
//    public long createDeal(OfferDto offerDto) {
//        long estate = offerDto.getEstateId();
//        User realtor = userRepo.findRealtorById(offerDto.getRealtorId());
//        Deal deal = new Deal(estate, realtor);
//        Deal savedDeal = dealRepo.save(deal);
//        return savedDeal.getId();
//    }
//
//    public void closeDeal(DealDto dealDto) {
//        throw new RuntimeException();
//    }
//
//    public void cancelDeal(EstateDto estateDto) {
//        throw new RuntimeException();
//    }
//
//    public long createOffer(EstateDto estateDto) {
//        throw new RuntimeException();
//    }
//
//    public void deleteOffer(EstateDto estateDto) {
//        throw new RuntimeException();
//    }
//
//    public void deleteAllOffer() {
//        throw new RuntimeException();
//    }
//}
