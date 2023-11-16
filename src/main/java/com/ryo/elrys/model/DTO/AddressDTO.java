package com.ryo.elrys.model.DTO;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AddressDTO {

    @Schema(name = "street", defaultValue = "123_Main_Street")
    private String street;

    @Schema(name = "city", defaultValue = "tokyo")
    private String city;

    @Schema(name = "postal_Code", defaultValue = "293847")
    private Integer postalCode;

    @Schema(name = "country", defaultValue = "japan")
    private String country;

}
