package com.ryo.elrys.model.DTO;

import java.util.List;

import javax.annotation.Nullable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateDTO {

    @Nullable
    private String email;

    @Nullable
    private String password;

    @Nullable
    private String username;

    @Nullable
    private String fullName;

    @Nullable
    private String dob;
    private Address address;
    private AdditionalInfo info;

    public UpdateDTO(AccountsDTO accountsDTO) {
        this.email = accountsDTO.getEmail();
        this.password = accountsDTO.getPassword();
    }

}

/**
 * InnerUpdateDTO
 */
@Data
class Address {

    @Nullable
    private String street;

    @Nullable
    private String city;

    @Nullable
    private Integer postalCode;

    @Nullable
    private String country;

}

/**
 * InnerUpdateDTO
 */
@Data
class AdditionalInfo {

    // Additional info

    @Nullable
    private Integer phone;

    @Nullable
    private String gender;

    @Nullable
    private Boolean subscription;

    @Nullable
    private List<String> preference;

    @Nullable
    private String occupation;

    // Education

    @Nullable
    private String degree;

    @Nullable
    private String major;

    @Nullable
    private String school;

    @Nullable
    private List<String> interests;

    @Nullable
    private List<String> skills;

    @Nullable
    private List<String> languages;

}

@Data
class Device {

    @Nullable
    private String device_type;

    @Nullable
    private String device_model;
}
