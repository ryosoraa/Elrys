package com.ryo.elrys.model.MAP;

import com.ryo.elrys.model.DTO.AccountsDTO;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LoginMap {

    private String id;
    private String email;
    private String timestamp;

    public LoginMap(AccountsDTO accountsDTO, String id) {
        this.id = id;
        this.email = accountsDTO.getEmail();
        this.timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));

    }
}