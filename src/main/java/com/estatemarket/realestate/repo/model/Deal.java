package com.estatemarket.realestate.repo.model;
import com.estatemarket.realestate.exceptions.api.enums.DealStatusEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "deal")
public class Deal implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "estate_id")
    private int estateId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "owner_id")
    private int ownerId;
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
    private DealStatusEnum status;

    public int getEstateId() {
        return estateId;
    }

    public int getOwnerId() {
        return ownerId;
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

    public DealStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DealStatusEnum status) {
        this.status = status;
    }

}
