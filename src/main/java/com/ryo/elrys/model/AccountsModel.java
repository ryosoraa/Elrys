package com.ryo.elrys.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccountsModel {

    @Schema(name = "email", defaultValue = "ryo@gmail.com")
    private String email;

    @Schema(name = "password", defaultValue = "ryo")
    private String password;
}
