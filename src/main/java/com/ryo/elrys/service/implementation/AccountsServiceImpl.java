package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.enums.Options;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.model.LoginModel;
import com.ryo.elrys.service.interfaces.AccountsService;
import com.ryo.elrys.utils.Equipment;

import com.ryo.elrys.utils.api.RequestApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    Equipment equipment;

    @Autowired
    RequestApiImpl requestApi;

    // REGISTER
    @Override
    public Object register(AccountsModel accountsModel) throws Exception {
        String idEncode = equipment.idEncoder(accountsModel);
        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);
        ObjectMapper mapper = new ObjectMapper();

        if (requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Customer already exists";
        }

        return requestApi.register(bodyUrl, mapper.writeValueAsString(new DataModel(accountsModel)));
    }

    // LOGIN
    @Override
    public Object login(AccountsModel accountsModel) throws Exception {
        Random random = new Random();
        ObjectMapper mapper = new ObjectMapper();
        String idEncode = equipment.idEncoder(accountsModel);

        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);
        String loginUrl = "http://192.168.20.90:9200/elrys_log/_doc/".concat(String.valueOf(random.nextLong()));

        JsonNode responds = requestApi.findById(bodyUrl);

        if (!responds.get("found").asBoolean()) {
            return "User not found";
        }

        JsonNode jsonNode = requestApi.login(loginUrl,
                mapper.writeValueAsString(new LoginModel(accountsModel, idEncode)));

        return responds;

    }

    // Find By Email
    @Override
    public Object findByEmail(String email) throws Exception {
        String bodyUrl = "http://192.168.20.90:9200/elrys/_search";
        String bodyRequest = String.format("{\"query\": {\"wildcard\": {\"email.keyword\": \"%s\"}}}", email);

        JsonNode jsonNode = requestApi.findByEmail(bodyUrl, bodyRequest);
        System.out.println(jsonNode.toPrettyString());

        if (!String.valueOf(jsonNode.at("/hits/hits/0/_source/email").asText()).equals(email)) {
            return "not found";
        }

        return jsonNode;
    }

    // Update
    @Override
    public Object update(DataModel dataModel) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String idEncode = equipment.idEncoder(dataModel);
        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);

        if (!requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Accounts not found";
        }

        System.out.println("masuk repo");
        return requestApi.register(bodyUrl, mapper.writeValueAsString(dataModel));

    }

    // Delete
    @Override
    public Object delete(AccountsModel accountsModel) throws Exception {
        String idEncode = equipment.idEncoder(accountsModel);
        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);
        String bodyUrl2 = "http://192.168.20.90:9200/elrys_log/_delete_by_query";
        String request = String.format("{\"query\": {\"term\": {\"id.keyword\": \"%s\"}}}", idEncode);

        if (!requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Accounts not found";
        }
        requestApi.delete(bodyUrl2, request);
        return requestApi.delete(bodyUrl, Options.WITHOUT_REQUEST.getOptions());
    }

}
