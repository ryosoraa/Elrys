package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
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
                .data(new DataResponse(registerDTO).dataResponse())
                .message("Successful Registration")
                .build();
    }

    @Override
    public BodyResponse<Object> login(LoginDTO LoginDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

}
