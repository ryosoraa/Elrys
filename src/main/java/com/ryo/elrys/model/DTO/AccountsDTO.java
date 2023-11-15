package com.ryo.elrys.model.DTO;

import lombok.Data;

import java.sql.Timestamp;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Data
public class AccountsDTO {

    @Schema(name = "email", nullable = false)
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String username;

}
