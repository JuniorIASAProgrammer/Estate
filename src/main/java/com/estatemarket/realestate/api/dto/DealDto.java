package com.estatemarket.realestate.api.dto;

import com.estatemarket.realestate.api.enums.DealStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
public class DealDto {
    private long estateId;
    private long ownerId;
    private long realtorId;
    private Timestamp time;
    private DealStatusEnum dealStatus;
}
