package com.ryo.elrys.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class LoginDTO {

    @Schema(name = "email", defaultValue = "ryo@gmail.com")
    private String email;

    @Schema(name = "password", defaultValue = "ryo")
    private String password;

    @Schema(name = "deviceType", defaultValue = "andorid")
    private String deviceType;

    @Schema(name = "deviceModel", defaultValue = "poco")
    private String deviceModel;
}
