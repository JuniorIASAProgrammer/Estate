package com.estatemarket.realestate.exceptions.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class UserDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String role;
}
