package com.ryo.elrys.service;

import com.ryo.elrys.elastic.Api;
import com.ryo.elrys.elastic.Client;
import com.ryo.elrys.model.DTO.AccountsDTO;
//import com.ryo.elrys.repository.AccountsRepository;
import com.ryo.elrys.model.MAP.AccountsMAP;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AccountsService {

//    @Autowired
//    AccountsRepository accountsRepository;

    @Autowired
    Api api;

    public void register(AccountsDTO accountsDTO){

        Random random = new Random();

        AccountsMAP accountsMAP = AccountsMAP
                .builder()
                .email(accountsDTO.getEmail())
                .password(accountsDTO.getPassword())
                .username(accountsDTO.getUsername())
                .build();

//        IndexRequest request = new IndexRequest("elrys")
//                .id(String.valueOf(random.nextLong()))
//                .source(accountsMAP);

        try {
            IndexResponse response =
                    api.client().index(
                    api.request(accountsMAP), RequestOptions.DEFAULT);

        } catch (Exception err){
            System.out.println(err);
        }


    }
}
