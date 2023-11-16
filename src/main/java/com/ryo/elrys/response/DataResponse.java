package com.ryo.elrys.response;

import java.sql.Timestamp;
import java.util.HashMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.DTO.LoginDTO;
import com.ryo.elrys.model.DTO.RegisterDTO;
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
    private String username;
    private String timesTamp;

    public HashMap<String, String> registerResponse() {

        HashMap<String, String> response = new HashMap<>();
        response.put("email", email);
        response.put("username", username);
        return response;

    }

    public HashMap<String, String> loginResponse() {

        HashMap<String, String> response = new HashMap<>();
        response.put("email", email);
        response.put("username", username);
        response.put("timestamp", timesTamp);
        return response;

    }

    public DataResponse(RegisterDTO registerDTO) {
        this.email = registerDTO.getEmail();
        this.username = registerDTO.getUsername();
    }

    public DataResponse(JsonNode jsonNode) {
        this.email = String.valueOf(jsonNode.at("/_source/email").asText());
        this.username = String.valueOf(jsonNode.at("/_source/username").asText());
        this.timesTamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
    }

}
