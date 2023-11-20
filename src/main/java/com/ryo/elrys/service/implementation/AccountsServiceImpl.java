package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.model.DTO.UpdateDTO;
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
    public BodyResponse<Object> register(AccountsDTO accountsDTO) throws JsonProcessingException {
        Object response = accountsRepository.register(accountsDTO);

        if (response.equals("Customer already exists")) {
            return BodyResponse.builder()
                    .status("Failed")
                    .data(null)
                    .message("Customer already exist")
                    .build();
        }

        return BodyResponse.builder()
                .status("Success")
                .data(new DataResponse(accountsDTO).registerResponse())
                .message("Successful Registration")
                .build();
    }

    @Override
    public BodyResponse<Object> login(AccountsDTO accountsDTO) throws JsonProcessingException {
        Object loginResponse = accountsRepository.login(accountsDTO);

        if (loginResponse.equals("User not found")) {
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

        if (findResponse.equals("not found")) {
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
    public BodyResponse<Object> update(UpdateDTO updateDTO) throws Exception {
        Object findResponse = accountsRepository.update(updateDTO);
        if (findResponse.equals("Accounts not found")) {
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
    public BodyResponse<Object> delete(AccountsDTO accountsDTO) throws Exception {
        Object findResponse = accountsRepository.delete(accountsDTO);
        if (findResponse.equals("Accounts not found")) {
            return BodyResponse.builder()
                    .status("Failed")
                    .message("Accounts nof found")
                    .build();
        }
        return BodyResponse.builder()
                .status("Success")
                .message("Delete Success")
                .build();
    }

}
