package com.ryo.elrys.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AdditionalInfoDTO {

    @Schema(name = "gender", defaultValue = "male")
    private String gender;

    @Schema(name = "subscription", defaultValue = "false")
    private Boolean subscription;

    @Schema(name = "preference", defaultValue = "Coding")
    private String preference;
}
