package com.estatemarket.realestate.repo.model;

import com.estatemarket.realestate.exceptions.api.enums.OfferEnum;
import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "offer")
public class Offer implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "estate_id")
    private int estateId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "realtor_id")
    private int realtorId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "time")
    private Timestamp time;
    @Basic
    @Column(name = "status")
    private OfferEnum status;

    public Offer() {
    }

    public Offer(int estateId, int realtorId, Timestamp time) {
        this.estateId = estateId;
        this.realtorId = realtorId;
        this.time = time;
        this.status = OfferEnum.SENT;
    }

    public int getEstateId() {
        return estateId;
    }

    public void setEstateId(int estateId) {
        this.estateId = estateId;
    }

    public int getRealtorId() {
        return realtorId;
    }

    public void setRealtorId(int realtorId) {
        this.realtorId = realtorId;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public OfferEnum getStatus() {
        return status;
    }

    public void setStatus(OfferEnum status) {
        this.status = status;
    }
}
