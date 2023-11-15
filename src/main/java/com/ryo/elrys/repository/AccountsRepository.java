package com.ryo.elrys.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.api.RequestApi;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.model.MAP.AccountsMAP;
import com.ryo.elrys.response.BodyResponse;
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
        String bodyUrl = "";
        String idEncode = equipment.idEncoder(registerDTO);

        if(!requestApi.findById(idEncode).isNull()){
            return "Customer already exist";
        }

        AccountsMAP accountsMAP = AccountsMAP.builder()
                .email(registerDTO.getEmail())
                .password(BCrypt.hashpw(registerDTO.getPassword(), BCrypt.gensalt()))
                .times(String.valueOf(new Timestamp(System.currentTimeMillis())))
                .build();

        JsonNode jsonNode = requestApi.register(bodyUrl, accountsMAP.register());
        return registerDTO;
    }
    
}
