package com.estatemarket.realestate.exceptions.api.dto;

import com.estatemarket.realestate.repo.model.Description;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class EstateDto {
    private String dealType;
    private Description description;
    private long owner;
}
