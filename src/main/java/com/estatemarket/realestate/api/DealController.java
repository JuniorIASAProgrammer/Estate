package com.estatemarket.realestate.api;

import com.estatemarket.realestate.repo.model.Deal;
import com.estatemarket.realestate.repo.model.Offer;
import com.estatemarket.realestate.service.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("EstateMarketplace/deal")
public class DealController {
    private final DealService dealService;

    //    Get all offers by estate - for client
    @GetMapping("/showOffers/estateId={estateId}")
    public ResponseEntity<List<Offer>> getByEstateId(@PathVariable long estateId) {
        final List<Offer> offerList = dealService.fetchByEstateId(estateId);
        return ResponseEntity.ok(offerList);
    }

//        Get all deals - for realtor
    @GetMapping("/showDeals")
    public ResponseEntity<List<Deal>> getByRealtorId() {
        final List<Deal> dealList = dealService.fetchByRealtorId();
        return ResponseEntity.ok(dealList);
    }

    //    Make a deal - for client
    @PostMapping("/createDeal/offerId={offerId}")
    public ResponseEntity<Void> createDeal(@PathVariable long offerId) {
        long id = dealService.createDeal(offerId);
        String location = String.format("/dealList/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    //    Make an offer - for realtor
    @PostMapping("/createOffer/estateId={estateId}")
    public ResponseEntity<Void> createOffer(@PathVariable long estateId) {
        long id = dealService.createOffer(estateId);
        String location = String.format("/offerList/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

//    Cancel offer - for realtor
    @DeleteMapping("/deleteOffer/estateId={estateId}")
    public ResponseEntity<Void> deleteOffer(@RequestBody long estateId) {
        dealService.deleteOffer(estateId);
        return ResponseEntity.noContent().build();
    }


//    Cancel the deal\
    @PatchMapping("/cancelDeal/dealId={dealId}")
    public ResponseEntity<Void> cancelDeal(@PathVariable long dealId) {
        dealService.cancelDeal(dealId);
        return ResponseEntity.noContent().build();
    }

//    Close the deal - for realtor
    @PatchMapping("/closeDeal/dealId={dealId}")
    public ResponseEntity<Void> closeDeal(@PathVariable long dealId) {
        dealService.closeDeal(dealId);
        return ResponseEntity.noContent().build();
    }
}
