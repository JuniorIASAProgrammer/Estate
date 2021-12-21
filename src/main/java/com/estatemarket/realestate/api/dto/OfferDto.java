package com.estatemarket.realestate.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class OfferDto {
    private long estateId;
    private long realtorId;
    private Timestamp datetime;
}
