package com.estatemarket.realestate.api.dto;

import com.estatemarket.realestate.api.enums.EstateDealEnum;
import com.estatemarket.realestate.repo.model.Description;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class EstateDto {
    private EstateDealEnum dealType;
    private Description description;
}
