package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.DTO.LoginDTO;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.repository.AccountsRepository;
import com.ryo.elrys.response.BodyResponse;
import com.ryo.elrys.response.DataResponse;
import com.ryo.elrys.service.interfaces.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    AccountsRepository accountsRepository;

    @Override
    public BodyResponse<Object> register(RegisterDTO registerDTO) throws JsonProcessingException {
        Object response = accountsRepository.register(registerDTO);

        if(response.equals("Customer already exists")){
            return BodyResponse.builder()
                    .status("Failed")
                    .data(null)
                    .message("Customer already exist")
                    .build();
        }

        return BodyResponse.builder()
                .status("Success")
                .data(new DataResponse(registerDTO).registerResponse())
                .message("Successful Registration")
                .build();
    }

    @Override
    public BodyResponse<Object> login(LoginDTO loginDTO) throws JsonProcessingException {
        Object loginResponse = accountsRepository.login(loginDTO);

        if (loginResponse.equals("User not found")){
            return BodyResponse.builder()
                    .status("User not found")
                    .message("Please register your account to continue")
                    .build();
        }

        return BodyResponse.builder()
                .status("Success")
                .data(new DataResponse((JsonNode) loginResponse).loginResponse())
                .message("Login successful")
                .build();
    }

    @Override
    public BodyResponse<Object> findByEmail(String email) throws Exception {
        Object findResponse = accountsRepository.findByEmail(email);

        if(findResponse.equals("not found")){
            return BodyResponse.builder()
                    .status("failed")
                    .message("Accounts not found")
                    .build();
        }
        return BodyResponse.builder()
                .status("Success")
                .data(findResponse)
                .message("Account in Discover")
                .build();
    }

    @Override
    public BodyResponse<Object> update(RegisterDTO registerDTO) throws Exception {
        Object findResponse =  accountsRepository.update(registerDTO);
        if(findResponse.equals("Accounts not found")){
            return BodyResponse.builder()
                    .status("Failed")
                    .message("Accounts nof found")
                    .build();
        }
        return BodyResponse.builder()
                .status("Success")
                .data(findResponse)
                .message("Update Success")
                .build();
    }

    @Override
    public BodyResponse<Object> delete(String email) throws Exception {
        return null;
    }

    @Override
    public BodyResponse<Object> findLogs(String email) throws Exception {
        return null;
    }

}
