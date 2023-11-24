package com.ryo.elrys.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class LoginModel {

    private String email;
    private String timestamp;

    public LoginModel(AccountsModel accountsModel) {
        this.email = accountsModel.getEmail();
        this.timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));

    }
}