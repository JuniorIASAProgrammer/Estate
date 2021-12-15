package com.estatemarket.realestate.exceptions.api;

import com.estatemarket.realestate.exceptions.api.dto.EstateDto;
import com.estatemarket.realestate.repo.model.Description;
import com.estatemarket.realestate.repo.model.Estate;
import com.estatemarket.realestate.service.EstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("/estate")
public class EstateController {

    private final EstateService estateService;

    @GetMapping
    public ResponseEntity<List<com.estatemarket.realestate.repo.model.Estate>> index() {
        final List<com.estatemarket.realestate.repo.model.Estate> estate = estateService.fetchAll();
        return ResponseEntity.ok(estate);
    }

    @GetMapping("/{id}")
    public ResponseEntity<com.estatemarket.realestate.repo.model.Estate> show(@PathVariable long id){
        try {
            final Estate estate = estateService.fetchById(id);
            return ResponseEntity.ok(estate);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody EstateDto estate){
        final String dealType = estate.getDealType();
        final Description description = estate.getDescription();
        final long owner = estate.getOwner();
        final long id = estateService.create(dealType, description, owner);
        final String location = String.format("/estate/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody EstateDto estate){
        final String dealType = estate.getDealType();
        final Description description = estate.getDescription();
        try{
            estateService.update(id, dealType, description);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        estateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
