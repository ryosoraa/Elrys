package com.ryo.elrys.model.DTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LoginDTO {

    private String id;
    private String email;
    private String timestamp;

    public LoginDTO(AccountsDTO accountsDTO, String id) {
        this.id = id;
        this.email = accountsDTO.getEmail();
        this.timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));

    }
}