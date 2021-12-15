package com.estatemarket.realestate.repo.model;

import com.estatemarket.realestate.exceptions.api.enums.EstateDealEnum;
import javax.persistence.*;

@Entity
@Table(name = "estate")
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "dealtype")
    private EstateDealEnum dealType;
    @Basic
    @Column(name = "owner")
    private long owner;
    private String city;
    private String district;
    private String adress;

    public Estate() {
    }

    public Estate(EstateDealEnum dealType, Description description, long owner) {
        this.dealType = dealType;
        this.owner = owner;
        this.city = description.getCity();
        this.district = description.getDistrict();
        this.adress = description.getAdress();
    }

    public long getId() {
        return id;
    }

    public EstateDealEnum getDealtype() {
        return dealType;
    }

    public void setDealType(EstateDealEnum dealType) {
        this.dealType = dealType;
    }

    public long getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setDescription(Description description) {
        this.city = description.getCity();
        this.district = description.getDistrict();
        this.adress = description.getAdress();
    }

}


