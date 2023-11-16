package com.ryo.elrys.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ryo.elrys.api.RequestApi;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.model.MAP.RegisterMAP;
import com.ryo.elrys.security.BCrypt;
import com.ryo.elrys.utils.Equipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public class AccountsRepository {

    @Autowired
    Equipment equipment;

    private final RequestApi requestApi = new RequestApi();

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
    
}
