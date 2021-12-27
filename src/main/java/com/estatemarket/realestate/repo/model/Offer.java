package com.estatemarket.realestate.repo.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Offer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Estate estate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User realtor;

    @Column(name = "date")
    private Timestamp datetime;

    public Offer() {
    }

    public Offer(Estate estate, User realtor) {
        this.estate = estate;
        this.realtor = realtor;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estate getEstate() {
        return estate;
    }

    public void setEstate(Estate estate) {
        this.estate = estate;
    }

    public User getRealtor() {
        return realtor;
    }

    public void setRealtor(User realtor) {
        this.realtor = realtor;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

}
