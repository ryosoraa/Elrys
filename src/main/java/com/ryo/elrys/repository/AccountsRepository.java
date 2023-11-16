package com.ryo.elrys.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.api.RequestApi;
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
    public Object register(RegisterDTO registerDTO) throws JsonProcessingException {
        String idEncode = equipment.idEncoder(registerDTO);
        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);

        if (requestApi.findById(bodyUrl).get("found").asBoolean()) {
            return "Customer already exists";
        }

        return requestApi.register(bodyUrl, new RegisterMAP(registerDTO).register());
        /* RegisterMAP registerMAP = new RegisterMAP(registerDTO);
        return requestApi.register(bodyUrl, registerMAP.register());*/
    }


    // LOGIN
    public Object login(LoginDTO loginDTO) throws JsonProcessingException {
        Random random = new Random();
        String idEncode = equipment.idEncoder(loginDTO);
        String loginEncode = equipment.loginEncode(loginDTO);

        String bodyUrl = "http://192.168.20.90:9200/elrys/_doc/".concat(idEncode);
        String loginUrl = "http://192.168.20.90:9200/elrys_log/_doc/".concat(String.valueOf(random.nextLong()));

        JsonNode responds = requestApi.findById(bodyUrl);

        if(!responds.get("found").asBoolean()){
            return "User not found";
        }

        JsonNode jsonNode = requestApi.login(loginUrl, new LoginMap(
                loginDTO, /*Paramater 1 untuk mengambil assets dari loginDTO*/
                idEncode, /*Paramater 2 Untuk set Accounts_id Reference*/
                String.valueOf(responds.at("/_source/username").asText())).login());
                /*Paramater 3 untuk set username, mengambil dari respond requestAPi karena si loginDTO tidak ada field username*/

        return responds;

    }
    
}
