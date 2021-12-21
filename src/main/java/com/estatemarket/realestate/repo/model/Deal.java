//package com.estatemarket.realestate.repo.model;
//import com.estatemarket.realestate.api.enums.DealStatusEnum;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.text.SimpleDateFormat;
//
//@Entity
//@Table(name = "deal")
//public class Deal implements Serializable {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    private int id;
//
//    @Column(name = "estate_id")
//    private long estateId;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @Column(name = "realtor_id")
//    private User realtorId;
//
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "time")
//    private SimpleDateFormat time;
//
//    @Column(name = "status")
//    private DealStatusEnum status;
//
//    public Deal() {
//    }
//
//    public Deal(long estateId, User realtorId) {
//        this.estateId = estateId;
//        this.realtorId = realtorId;
//        this.time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public long getEstateId() {
//        return estateId;
//    }
//
//
//    public long getRealtorId() {
//        return realtorId.getId();
//    }
//
//    public SimpleDateFormat getTime() {
//        return time;
//    }
//
//    public DealStatusEnum getStatus() {
//        return status;
//    }
//
//    public void setStatus(DealStatusEnum status) {
//        this.status = status;
//    }
//
//}
