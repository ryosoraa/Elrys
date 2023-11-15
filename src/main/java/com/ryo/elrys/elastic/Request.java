package com.ryo.elrys.elastic;

import com.ryo.elrys.model.MAP.AccountsMAP;
import org.elasticsearch.action.index.IndexRequest;

import java.util.Random;

public class Request {

    public IndexRequest request(AccountsMAP accountsMAP){
        Random random = new Random();
        return new IndexRequest("elrys")
                .id(String.valueOf(random.nextLong()))
                .source(accountsMAP);
    }
}
