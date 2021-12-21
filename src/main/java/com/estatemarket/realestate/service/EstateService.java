package com.estatemarket.realestate.service;

import com.estatemarket.realestate.api.dto.EstateDto;
import com.estatemarket.realestate.api.enums.EstateDealEnum;
import com.estatemarket.realestate.repo.EstateRepo;
import com.estatemarket.realestate.repo.UserRepo;
import com.estatemarket.realestate.repo.model.Description;
import com.estatemarket.realestate.repo.model.Estate;
import com.estatemarket.realestate.repo.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public final class EstateService {

    private final EstateRepo estateRepo;
    private final UserRepo userRepo;

    public List<Estate> fetchAll(){
        return estateRepo.findAll();
    }

    public Estate fetchById(long id) throws IllegalArgumentException{
        return estateRepo.findById(id).orElseThrow(()-> new IllegalArgumentException("User not found"));
    }

    public List<Estate> fetchByAuthorisedUser() {
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User owner = userRepo.findByEmail(currentUserEmail);
        return estateRepo.findAllByOwner(owner);
    }

    public long create(EstateDto estate){
        EstateDealEnum dealType = estate.getDealType();
        Description description = estate.getDescription();
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User owner = userRepo.findByEmail(currentUserEmail);
        Estate newEstate = new Estate(dealType, description, owner);
        Estate savedEstate = estateRepo.save(newEstate);
        return savedEstate.getId();
    }

    public void update(long id, EstateDto estateDto){
        EstateDealEnum dealType = estateDto.getDealType();
        String city = estateDto.getDescription().getCity();
        String district = estateDto.getDescription().getDistrict();
        String adress = estateDto.getDescription().getAdress();
        final Estate maybeEstate = estateRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Estate not found"));
        if (dealType!=null && !dealType.toString().isEmpty() && dealType!=maybeEstate.getDealtype()) maybeEstate.setDealType(dealType);
        if (city!=null && !city.isEmpty()) maybeEstate.setCity(city);
        if (district!=null && !district.isEmpty()) maybeEstate.setDistrict(district);
        if (adress!=null && !adress.isEmpty()) maybeEstate.setAddress(adress);
        estateRepo.save(maybeEstate);
    }

    public void delete(long id){
        String currentUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        User owner = userRepo.findByEmail(currentUserEmail);
        final Estate maybeEstate = estateRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Estate not found"));
        if (maybeEstate.getOwner() == owner){
            estateRepo.deleteById(id);
        }
        else throw new IllegalArgumentException("Access denied");
    }
}
