package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.enums.BodyUrl;
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

    @Autowired
    ObjectMapper mapper;

    // REGISTER
    @Override
    public Object register(AccountsModel accountsModel) throws Exception {
        String bodyUrl = BodyUrl.MAIN_DOC.getUrl().concat(equipment.idEncoder(accountsModel));
        String request = String.format("{\"query\": {\"bool\": {\"must\": [{\"term\": {\"email.keyword\": {\"value\": \"%s\"}}}]}}}", accountsModel.getEmail());

        if(requestApi.findByEmail(BodyUrl.MAIN_SEARCH.getUrl(), request).at("/hits/total/value").toString().equals("1")){
            return "Customer already exists";
        }

        System.out.println(requestApi.findByEmail(BodyUrl.MAIN_SEARCH.getUrl(), request).toPrettyString());
        return requestApi.register(bodyUrl, mapper.writeValueAsString(new DataModel(accountsModel)));
    }

    // LOGIN
    @Override
    public Object login(AccountsModel accountsModel) throws Exception {
        Random random = new Random();
        String idEncode = equipment.idEncoder(accountsModel);

        JsonNode responds = requestApi.findById(BodyUrl.MAIN_DOC.getUrl().concat(idEncode));

        if (!responds.get("found").asBoolean()) {
            return "User not found";
        }

        JsonNode jsonNode = requestApi.login(
                BodyUrl.LOG_DOC.getUrl().concat(String.valueOf(random.nextLong())),
                mapper.writeValueAsString(new LoginModel(accountsModel, responds.at("/_source/uuid").asText()))
        );

        return responds;

    }

    // Find By Email
    @Override
    public Object findByEmail(String email) throws Exception {

        String request = String.format("{\"query\": {\"wildcard\": {\"email.keyword\": \"%s\"}}}", email);

        JsonNode jsonNode = requestApi.findByEmail(BodyUrl.MAIN_SEARCH.getUrl(), request);
        if (!String.valueOf(jsonNode.at("/hits/hits/0/_source/email").asText()).equals(email)) {
            return "not found";
        }

        return jsonNode;
    }

    // Update
    @Override
    public Object update(DataModel dataModel) throws Exception {

        String request = String.format("{\"query\": {\"bool\": {\"must\": [{\"term\": {\"email.keyword\": {\"value\": \"%s\"}}}]}}}", dataModel.getEmail());
        JsonNode response = requestApi.findByEmail(BodyUrl.MAIN_SEARCH.getUrl(), request);

        if(!requestApi.findByEmail(BodyUrl.MAIN_SEARCH.getUrl(), request).at("/hits/total/value").toString().equals("1")){
            return "Accounts not found";
        }
        dataModel.setUuid(response.at("/hits/hits/0/_source/uuid").asText());

        return requestApi.register(BodyUrl.MAIN_DOC.getUrl().concat(String.valueOf(dataModel.getUuid())), mapper.writeValueAsString(dataModel));

    }

    // Delete
    @Override
    public Object delete(AccountsModel accountsModel) throws Exception {
        String idEncode = equipment.idEncoder(accountsModel);
        String bodyUrl = BodyUrl.MAIN_SEARCH.getUrl().concat(idEncode);

        String request = String.format("{\"query\": {\"term\": {\"id.keyword\": \"%s\"}}}", idEncode);

        if (!requestApi.findById(BodyUrl.MAIN_DOC.getUrl().concat(idEncode)).get("found").asBoolean()) {
            return "Accounts not found";
        }
        requestApi.delete(BodyUrl.LOG_DELETE_QUERY.getUrl(), request);
        return requestApi.delete(bodyUrl, Options.WITHOUT_REQUEST.getOptions());
    }

}
