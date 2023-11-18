package com.ryo.elrys.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.api.RequestApi;
import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.model.DTO.DeleteDTO;
import com.ryo.elrys.model.DTO.LoginDTO;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.model.MAP.LoginMap;
import com.ryo.elrys.model.MAP.RegisterMAP;
import com.ryo.elrys.security.BCrypt;
import com.ryo.elrys.utils.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Random;

@Repository
public class AccountsRepository {

    @Autowired
    Equipment equipment;

    private final RequestApi requestApi = new RequestApi();

    // REGISTER
    public Object register(AccountsDTO accountsDTO) throws JsonProcessingException {
        String idEncode = equipment.idEncoder(accountsDTO);
        String bodyUrl = "http://192.168.43.9:9200/elrys/_doc/".concat(idEncode);

        if (requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Customer already exists";
        }

        return requestApi.register(bodyUrl, new RegisterMAP(accountsDTO).user());
        /*
         * RegisterMAP registerMAP = new RegisterMAP(accountsDTO);
         * return requestApi.register(bodyUrl, registerMAP.register());
         */
    }

    // LOGIN
    public Object login(LoginDTO loginDTO) throws JsonProcessingException {
        Random random = new Random();
        String idEncode = equipment.idEncoder(loginDTO);

        String bodyUrl = "http://192.168.43.9:9200/elrys/_doc/".concat(idEncode);
        String loginUrl = "http://192.168.43.9:9200/elrys_log/_doc/".concat(String.valueOf(random.nextLong()));

        JsonNode responds = requestApi.findById(bodyUrl);

        if (!responds.get("found").asBoolean()) {
            return "User not found";
        }

        JsonNode jsonNode = requestApi.login(loginUrl, new LoginMap(
                loginDTO, /* Parameter 1 untuk mengambil assets dari loginDTO */
                idEncode, /* Parameter 2 Untuk set Accounts_id Reference */
                String.valueOf(responds.at("/_source/username").asText())).login());
        /*
         * Parameter 3 untuk set username, mengambil dari respond requestAPi karena si
         * loginDTO tidak ada field username
         */

        return responds;

    }

    // Find By Email
    public Object findByEmail(String email) throws JsonProcessingException {
        String bodyUrl = "http://192.168.43.9:9200/elrys/_search";
        String bodyRequest = String.format("{\"query\": {\"wildcard\": {\"email.keyword\": \"%s\"}}}", email);

        JsonNode jsonNode = requestApi.findByEmail(bodyUrl, bodyRequest);

        if (!String.valueOf(jsonNode.at("/hits/hits/0/_source/email").asText()).equals(email)) {
            return "not found";
        }

        return jsonNode;
    }

    // Update
    public Object update(RegisterDTO registerDTO) throws JsonProcessingException {
        String idEncode = equipment.idEncoder(registerDTO);
        String bodyUrl = "http://192.168.43.9:9200/elrys/_doc/".concat(idEncode);

        if (!requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Accounts not found";
        }

        return requestApi.register(bodyUrl, new RegisterMAP(registerDTO).user());

    }

    // Delete
    public Object delete(DeleteDTO deleteDTO) throws JsonProcessingException {
        String idEncode = equipment.idEncoder(deleteDTO);
        String bodyUrl = "http://192.168.43.9:9200/elrys/_doc/".concat(idEncode);

        if (!requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Accounts not found";
        }

        return requestApi.delete(bodyUrl);
    }

}
