package com.ryo.elrys.service;

import com.ryo.elrys.elastic.Api;
import com.ryo.elrys.model.DTO.AccountsDTO;
//import com.ryo.elrys.repository.AccountsRepository;
import com.ryo.elrys.model.MAP.AccountsMAP;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.rest.RestStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

@Service
public class AccountsService {

    @Autowired
    Api api;

    public String register(AccountsDTO accountsDTO) throws IOException {

        if (accountsDTO.getEmail() == null || accountsDTO.getPassword() == null
                || accountsDTO.getUsername() == null) {
            return "Something Missing";
        }

        Random random = new Random();

        AccountsMAP accountsMAP = AccountsMAP
                .builder()
                .email(accountsDTO.getEmail())
                .password(accountsDTO.getPassword())
                .username(accountsDTO.getUsername())
                .times(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build();

        IndexResponse response = api.client().index(api.request(accountsMAP), RequestOptions.DEFAULT);

        return String.valueOf(response.status());

    }
}
