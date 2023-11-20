package com.ryo.elrys.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.api.RequestApi;
import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.model.DTO.UpdateDTO;
import com.ryo.elrys.model.MAP.LoginMap;
import com.ryo.elrys.security.BCrypt;
import com.ryo.elrys.utils.Equipment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Random;

@Repository
public class AccountsRepository {

    @Autowired
    Equipment equipment;

    private final RequestApi requestApi = new RequestApi();

    // REGISTER
    public Object register(AccountsDTO accountsDTO) throws JsonProcessingException {
        String idEncode = equipment.idEncoder(accountsDTO);
        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);
        ObjectMapper mapper = new ObjectMapper();

        System.out.println(requestApi.findById(bodyUrl).toPrettyString());
        System.out.println(requestApi.findById(bodyUrl).get("found").asBoolean());

        if (requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Customer already exists";
        }

        return requestApi.register(bodyUrl, mapper.convertValue(new UpdateDTO(accountsDTO), Map.class));
    }

    // LOGIN
    public Object login(AccountsDTO accountsDTO) throws JsonProcessingException {
        Random random = new Random();
        ObjectMapper mapper = new ObjectMapper();

        String idEncode = equipment.idEncoder(accountsDTO);

        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);
        String loginUrl = "http://192.168.20.90:9200/elrys_log/_doc/".concat(String.valueOf(random.nextLong()));

        JsonNode responds = requestApi.findById(bodyUrl);

        if (!responds.get("found").asBoolean()) {
            return "User not found";
        }

        JsonNode jsonNode = requestApi.login(loginUrl,
                mapper.convertValue(new LoginMap(accountsDTO, idEncode), Map.class));
        System.out.println(jsonNode);

        return responds;

    }

    // Find By Email
    public Object findByEmail(String email) throws JsonProcessingException {
        String bodyUrl = "http://192.168.20.90:9200/elrys/_search";
        String bodyRequest = String.format("{\"query\": {\"wildcard\": {\"email.keyword\": \"%s\"}}}", email);

        JsonNode jsonNode = requestApi.findByEmail(bodyUrl, bodyRequest);

        if (!String.valueOf(jsonNode.at("/hits/hits/0/_source/email").asText()).equals(email)) {
            return "not found";
        }

        return jsonNode;
    }

    // Update
    public Object update(UpdateDTO updateDTO) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String idEncode = equipment.idEncoder(updateDTO);
        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);

        if (!requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Accounts not found";
        }

        System.out.println("masuk repo");
        return requestApi.register(bodyUrl, mapper.convertValue(updateDTO, Map.class));

    }

    // Delete
    public Object delete(AccountsDTO accountsDTO) throws JsonProcessingException {
        String idEncode = equipment.idEncoder(accountsDTO);
        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);

        if (!requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Accounts not found";
        }

        return requestApi.delete(bodyUrl);
    }

}
