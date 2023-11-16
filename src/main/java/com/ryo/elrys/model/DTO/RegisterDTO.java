package com.ryo.elrys.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class RegisterDTO {

    @Schema(name = "username", defaultValue = "ryosoraa")
    private String username;

    @Schema(name = "email", defaultValue = "ryo@gmail.com")
    private String email;

    @Schema(name = "password", defaultValue = "ryo")
    private String password;

    @Schema(name = "phone", defaultValue = "128370")
    private Integer phone;

    @Schema(name = "fullName", defaultValue = "rio dwi saputra")
    private String fullName;

    @Schema(name = "dateOfBirth", defaultValue = "2000-12-12")
    private String dateOfBirth;

    @Schema(name = "gender", defaultValue = "male")
    private String gender;

    @Schema(name = "street", defaultValue = "123_Main_Street")
    private String street;

    @Schema(name = "city", defaultValue = "tokyo")
    private String city;

    @Schema(name = "postalCode", defaultValue = "293847")
    private Integer postalCode;

    @Schema(name = "country", defaultValue = "japan")
    private String country;

    @Schema(name = "subscription", defaultValue = "false")
    private Boolean subscription;

    @Schema(name = "preference", defaultValue = "[\"sports\", \"technology\"]")
    private List<String> preference;

}
