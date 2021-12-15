package com.estatemarket.realestate.service;

import com.estatemarket.realestate.exceptions.api.enums.EstateDealEnum;
import com.estatemarket.realestate.repo.EstateRepo;
import com.estatemarket.realestate.repo.model.Description;
import com.estatemarket.realestate.repo.model.Estate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class EstateService {

    private final EstateRepo estateRepo;

    public List<Estate> fetchAll(){
        return estateRepo.findAll();
    }

    public Estate fetchById(long id) throws IllegalArgumentException{
        final Optional<Estate> maybeEstate = estateRepo.findById(id);
        if (maybeEstate.isEmpty()) throw new IllegalArgumentException("Announcment not found");
        else return maybeEstate.get();
    }

    public long create(String dealType, Description description, long owner){
        EstateDealEnum estateDealEnum = EstateDealEnum.valueOf(dealType);
        final Estate estate = new Estate(estateDealEnum, description, owner);
        final Estate savedEstate = estateRepo.save(estate);
        return savedEstate.getId();
    }

    public void update(long id, String dealType, Description description){
        final Optional<Estate> maybeEstate = estateRepo.findById(id);
        if (maybeEstate.isEmpty()) throw new IllegalArgumentException("User not found");

        final Estate estate = maybeEstate.get();
        EstateDealEnum estateDealEnum = EstateDealEnum.valueOf(dealType);
        if (dealType != null && !dealType.isBlank()) estate.setDealType(estateDealEnum);
        if (!description.isEmpty()) estate.setDescription(description);
        estateRepo.save(estate);
    }

    public void delete(long id){
        estateRepo.deleteById(id);
    }
}
