package com.estatemarket.realestate.repo.model;
import com.estatemarket.realestate.api.enums.DealStatusEnum;

import javax.persistence.*;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "deal")
public class Deal {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Estate estate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User realtor;

    @Column(name = "time")
    private SimpleDateFormat time;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private DealStatusEnum status;

    public Deal() {
    }

    public Deal(Estate estate, User realtor) {
        this.estate = estate;
        this.realtor = realtor;
        this.time = null;
        this.status = DealStatusEnum.STRIKED;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Estate getEstateId() {
        return estate;
    }

    public User getRealtorId() {
        return realtor;
    }

    public SimpleDateFormat getTime() {
        return time;
    }

    public DealStatusEnum getStatus() {
        return status;
    }

    public void setStatus(DealStatusEnum status) {
        this.status = status;
    }

}
