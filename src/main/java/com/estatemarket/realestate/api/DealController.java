//package com.estatemarket.realestate.api;
//
//import com.estatemarket.realestate.api.dto.DealDto;
//import com.estatemarket.realestate.api.dto.EstateDto;
//import com.estatemarket.realestate.api.dto.OfferDto;
//import com.estatemarket.realestate.repo.model.Deal;
//import com.estatemarket.realestate.repo.model.Offer;
//import com.estatemarket.realestate.service.DealService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.net.URI;
//import java.util.List;
//
//@RequiredArgsConstructor
//@RestController
//@RequestMapping("deal")
//public class DealController {
//    private final DealService dealService;
//
//    //    Get all offers by estate - for client
//    @GetMapping("/showOffers/estateId={estateId}")
//    public ResponseEntity<List<Offer>> fetchAllByEstateId(@PathVariable long estateId) {
//        final List<Offer> offerList = dealService.fetchAllByEstateId(estateId);
//        return ResponseEntity.ok(offerList);
//    }
//
//    //    Get all deals - for realtor
//    @GetMapping("/showDeals/realtorId={realtorId}")
//    public ResponseEntity<List<Deal>> fetchAllByRealtorId(@PathVariable long realtorId) {
//        final List<Deal> dealList = dealService.fetchAllByRealtorId(realtorId);
//        return ResponseEntity.ok(dealList);
//    }
//
//    //    Make a deal - for client
//    @PostMapping("/createDeal")
//    public ResponseEntity<Void> createDeal(@RequestBody OfferDto offerDto) {
//        long id = dealService.createDeal(offerDto);
//        String location = String.format("/dealList/%d", id);
//        return ResponseEntity.created(URI.create(location)).build();
//    }
//
//    //    Make an offer - for realtor
//    @PostMapping("/createOffer")
//    public ResponseEntity<Void> createOffer(@RequestBody EstateDto estateDto) {
//        long id = dealService.createOffer(estateDto);
//        String location = String.format("/offerList/%d", id);
//        return ResponseEntity.created(URI.create(location)).build();
//    }
//
////    Cancel offer - for realtor
//    @DeleteMapping()
//    public ResponseEntity<Void> deleteOffer(@RequestBody EstateDto estateDto) {
//        dealService.deleteOffer(estateDto);
//        return ResponseEntity.noContent().build();
//    }
//
////    Clear offer list
//    @DeleteMapping()
//    public ResponseEntity<Void> deleteAllOffer() {
//        dealService.deleteAllOffer();
//        return ResponseEntity.noContent().build();
//    }
//
////    Cancel the deal\
//    @PatchMapping()
//    public ResponseEntity<Void> cancelDeal(@RequestBody EstateDto estateDto) {
//        dealService.cancelDeal(estateDto);
//        return ResponseEntity.noContent().build();
//    }
//
////    Close the deal - for realtor
//    @PatchMapping("/closeDeal/")
//    public ResponseEntity<Void> closeDeal(@RequestBody DealDto dealDto) {
//        dealService.closeDeal(dealDto);
//        return ResponseEntity.noContent().build();
//    }
//}
