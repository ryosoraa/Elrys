package com.ryo.elrys.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccountsDTO {

    @Schema(name = "email", defaultValue = "ryo@gmail.com")
    private String email;

    @Schema(name = "password", defaultValue = "ryo")
    private String password;
}
