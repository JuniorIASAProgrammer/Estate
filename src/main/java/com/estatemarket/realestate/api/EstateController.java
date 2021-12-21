package com.estatemarket.realestate.api;

import com.estatemarket.realestate.api.dto.EstateDto;
import com.estatemarket.realestate.repo.model.Estate;
import com.estatemarket.realestate.service.EstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("EstateMarketplace/estate")
public class EstateController {

    private final EstateService estateService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Estate>> getAll() {
        final List<Estate> estateList = estateService.fetchAll();
        return ResponseEntity.ok(estateList);
    }

    @GetMapping("/getMyEstate")
    public ResponseEntity<List<Estate>> getByAuthorisedUser() {
        final List<Estate> estateList = estateService.fetchByAuthorisedUser();
        return ResponseEntity.ok(estateList);
    }

    @GetMapping("/getById/id={id}")
    public ResponseEntity<Estate> getById(@PathVariable long id){
        try {
            Estate estate = estateService.fetchById(id);
            return ResponseEntity.ok(estate);
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody EstateDto estate){
        long id = estateService.create(estate);
        String location = String.format("/estate/%d", id);
        return ResponseEntity.created(URI.create(location)).build();
    }

    @PatchMapping("/update/id={id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody EstateDto estate){
        try{
            estateService.update(id, estate);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/id={id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        estateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
