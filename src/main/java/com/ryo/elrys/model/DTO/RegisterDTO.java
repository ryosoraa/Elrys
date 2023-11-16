package com.ryo.elrys.model.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterDTO {

    private String username;
    private String email;
    private String password;
    private String fullName;
    private String dob;
    private AddressDTO addressDTO;
    private String phone;
    private AdditionalInfoDTO additionalInfoDTO;

}
