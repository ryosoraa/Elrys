package com.ryo.elrys.response;

import java.sql.Timestamp;
import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.model.DTO.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataResponse {

    private String email;
    private String timesTamp;

    public HashMap<String, String> registerResponse() {

        HashMap<String, String> response = new HashMap<>();
        response.put("email", email);
        return response;

    }

    public HashMap<String, String> loginResponse() {

        HashMap<String, String> response = new HashMap<>();
        response.put("email", email);
        response.put("timestamp", timesTamp);
        return response;

    }

    public DataResponse(AccountsDTO accountsDTO) {
        this.email = accountsDTO.getEmail();
    }

    public DataResponse(UserDTO userDTO) {
        this.email = userDTO.getEmail();
    }

    public DataResponse(JsonNode jsonNode) {
        this.email = String.valueOf(jsonNode.at("/_source/email").asText());
        this.timesTamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
    }

}
