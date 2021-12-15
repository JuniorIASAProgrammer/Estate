package com.estatemarket.realestate.repo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "estate_pic", schema = "real_estate", catalog = "")
public class EstatePic {
    @Id
    @Basic
    @Column(name = "estate_id")
    private int estateId;
    @Basic
    @Column(name = "link")
    private String link;

    public int getEstateId() {
        return estateId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
