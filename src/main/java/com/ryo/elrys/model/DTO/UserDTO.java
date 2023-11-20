package com.ryo.elrys.model.DTO;

import java.util.List;

import javax.annotation.Nullable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @Nullable
    @Schema(name = "email", defaultValue = "ryo@gmail.com")
    private String email;

    @Nullable
    @Schema(name = "password", defaultValue = "ryo")
    private String password;

    @Nullable
    @Schema(name = "userName", defaultValue = "ryosoraa")
    private String username;

    @Nullable
    @Schema(name = "fullName", defaultValue = "ryosoraa")
    private String fullName;

    @Nullable
    @Schema(name = "dateOfBirth", defaultValue = "2006-7-31")
    private String dateOfBirth;

    @Schema(name = "address")
    private Address address;

    @Schema(name = "additional_info")
    private AdditionalInfo additional_info;

    public UserDTO(AccountsDTO accountsDTO) {
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
    @Schema(name = "street", defaultValue = "123 Main Street")
    private String street;

    @Nullable
    @Schema(name = "city", defaultValue = "tokyo")
    private String city;

    @Nullable
    @Schema(name = "postalCode", defaultValue = "839724")
    private Integer postalCode;

    @Nullable
    @Schema(name = "country", defaultValue = "japan")
    private String country;

}

/**
 * InnerUpdateDTO
 */
@Data
class AdditionalInfo {

    // Additional info

    @Nullable
    @Schema(name = "phone", defaultValue = "927341802")
    private Integer phone;

    @Nullable
    @Schema(name = "gender", defaultValue = "male")
    private String gender;

    @Nullable
    @Schema(name = "newsletter_subscription", defaultValue = "true")
    private Boolean newsletter_subscription;

    @Nullable
    @Schema(name = "preference", defaultValue = "[\"game\", \"coding\"]")
    private List<String> preference;

    @Nullable
    @Schema(name = "occupation", defaultValue = "Backend Developer")
    private String occupation;

    // Education

    @Nullable
    @Schema(name = "degree", defaultValue = "Bachelor of Science")
    private String degree;

    @Nullable
    @Schema(name = "major", defaultValue = "Computer Science")
    private String major;

    @Nullable
    @Schema(name = "school", defaultValue = "Hogwarts")
    private String school;

    @Nullable
    @Schema(name = "interests", defaultValue = "[\"coding\", \"reading\", \"JKT48\"]")
    private List<String> interests;

    @Nullable
    @Schema(name = "skills", defaultValue = "[\"Java\", \"C#\", \"Python\"]")
    private List<String> skills;

    @Nullable
    @Schema(name = "languages", defaultValue = "[\"English\", \"Japan\"]")
    private List<String> languages;

}
