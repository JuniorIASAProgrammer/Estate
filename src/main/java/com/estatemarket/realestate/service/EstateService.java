package com.estatemarket.realestate.service;

import com.estatemarket.realestate.api.dto.EstateDto;
import com.estatemarket.realestate.api.enums.EstateDealEnum;
import com.estatemarket.realestate.repo.EstateRepo;
import com.estatemarket.realestate.repo.UserRepo;
import com.estatemarket.realestate.repo.model.Description;
import com.estatemarket.realestate.repo.model.Estate;
import com.estatemarket.realestate.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public final class EstateService {

    private final EstateRepo estateRepo;
    private final UserRepo userRepo;

    public List<Estate> fetchAll(){
        return estateRepo.findAll();
    }

    public Estate fetchById(long id) throws IllegalArgumentException{
        final Optional<Estate> maybeEstate = estateRepo.findById(id);
        if (maybeEstate.isEmpty()) throw new IllegalArgumentException("Estate not found");
        else return maybeEstate.get();
    }

    public long create(EstateDto estate){
        EstateDealEnum dealType = estate.getDealType();
        Description description = estate.getDescription();
        long ownerId = estate.getOwner();
        User owner = userRepo.findOwnerById(ownerId);
        Estate newEstate = new Estate(dealType, description, owner);
        final Estate savedEstate = estateRepo.save(newEstate);
        return savedEstate.getId();
    }

    public void update(long id, EstateDto estateDto){
        EstateDealEnum dealType = estateDto.getDealType();
        Description description = estateDto.getDescription();
        final Optional<Estate> maybeEstate = estateRepo.findById(id);
        if (maybeEstate.isEmpty()) throw new IllegalArgumentException("Estate not found");
        Estate estate = maybeEstate.get();
        if (!dealType.toString().isEmpty() && dealType!=estate.getDealtype()) estate.setDescription(description);
        if (!description.isEmpty()) estate.setDescription(description);
        estateRepo.save(estate);
    }

    public void delete(long id){
        estateRepo.deleteById(id);
    }
}
