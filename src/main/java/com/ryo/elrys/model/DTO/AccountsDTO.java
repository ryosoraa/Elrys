package com.ryo.elrys.model.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class AccountsDTO {

    private String email;
    private String password;
    private String username;

}
