package com.estatemarket.realestate.repo.model;

import javax.persistence.*;

@Entity
@Table(name = "description")
public class Description {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String city;
    private String district;
    private String adress;

    public Description() {
    }

    public Description(String city, String district, String adress) {
        this.city = city;
        this.district = district;
        this.adress = adress;
    }

    public long getId() {
        return id;
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

    public boolean isEmpty() {
        return !(this.city != null || this.district != null || this.adress != null);
    }
}
