//package com.estatemarket.realestate.repo.model;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.sql.Timestamp;
//
//@Entity
//@Table(name = "feedback")
//public class Feedback {
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "from")
//    private int from;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "to")
//    private int to;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Id
//    @Column(name = "time")
//    private Timestamp time;
//    @Basic
//    @Column(name = "rating")
//    private int rating;
//    @Basic
//    @Column(name = "comment")
//    private String comment;
//
//
//    public int getFrom() {
//        return from;
//    }
//
//    public void setFrom(int from) {
//        this.from = from;
//    }
//
//    public int getTo() {
//        return to;
//    }
//
//    public void setTo(int to) {
//        this.to = to;
//    }
//
//    public Timestamp getTime() {
//        return time;
//    }
//
//    public void setTime(Timestamp time) {
//        this.time = time;
//    }
//
//    public int getRating() {
//        return rating;
//    }
//
//    public void setRating(int rating) {
//        this.rating = rating;
//    }
//
//    public String getComment() {
//        return comment;
//    }
//
//    public void setComment(String comment) {
//        this.comment = comment;
//    }
//
//}
