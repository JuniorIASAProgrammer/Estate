package com.estatemarket.realestate.repo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
public class Offer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "estate_id")
    private int estateId;

    @Column(name = "realtor_id")
    private int realtorId;

    @Column(name = "date")
    private Timestamp datetime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Offer offer = (Offer) o;
        return id == offer.id && estateId == offer.estateId && realtorId == offer.realtorId && Objects.equals(datetime, offer.datetime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estateId, realtorId, datetime);
    }
}
