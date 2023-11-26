package com.ryo.elrys.model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Data;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;

import java.sql.Timestamp;

@Data
public class LoginModel {

    private
    String email;
    private String userAgent;
    private String timestamp;

    public LoginModel(AccountsModel accountsModel, HttpServletRequest requestHeader) {
        this.email = accountsModel.getEmail();
        this.timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
        this.userAgent = requestHeader.getHeader(HttpHeaders.USER_AGENT);

    }
}