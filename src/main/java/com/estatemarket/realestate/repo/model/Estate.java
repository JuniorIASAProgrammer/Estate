package com.estatemarket.realestate.repo.model;

import com.estatemarket.realestate.api.enums.EstateDealEnum;
import javax.persistence.*;

@Entity
@Table(name = "estate")
public class Estate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "dealtype")
    private EstateDealEnum dealType;

    @ManyToOne(fetch = FetchType.EAGER)
    private User owner;

    @Column(name = "city")
    private String city;

    @Column(name = "district")
    private String district;

    @Column(name = "address")
    private String address;


    public Estate() {
    }

    public Estate(EstateDealEnum dealType, Description description, User owner) {
        this.dealType = dealType;
        this.owner = owner;
        this.city = description.getCity();
        this.district = description.getDistrict();
        this.address = description.getAdress();
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

    public User getOwner() {
        return owner;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String adress) {
        this.address = adress;
    }
}


