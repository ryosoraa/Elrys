package com.ryo.elrys.response;

import java.util.HashMap;

import com.ryo.elrys.model.DTO.AccountsDTO;

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

    public HashMap<String, String> dataResponse() {

        HashMap<String, String> response = new HashMap<>();
        response.put("email", email);
        response.put("username", username);
        return response;

    }

    public DataResponse(AccountsDTO accountsDTO) {
        this.email = accountsDTO.getEmail();
        this.username = accountsDTO.getUsername();
    }

    public DataResponse(RegisterDTO registerDTO) {
        this.email = registerDTO.getEmail();
        this.username = registerDTO.getUsername();
    }

}
