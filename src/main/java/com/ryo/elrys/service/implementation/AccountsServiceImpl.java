package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ryo.elrys.model.DTO.LoginDTO;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.model.MAP.AccountsMAP;
import com.ryo.elrys.repository.AccountsRepository;
import com.ryo.elrys.response.BodyResponse;
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
        if(response.equals("Customer already exist")){
            return BodyResponse.builder()
                    .status("Failed")
                    .message("Customer already exist")
                    .build();
        }
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    @Override
    public BodyResponse<Object> login(LoginDTO LoginDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

}
