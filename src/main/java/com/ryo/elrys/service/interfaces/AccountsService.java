package com.ryo.elrys.service.interfaces;

import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.model.DTO.UserDTO;
import com.ryo.elrys.response.BodyResponse;

public interface AccountsService {

    BodyResponse<Object> register(AccountsDTO accountsDTO) throws Exception;

    BodyResponse<Object> login(AccountsDTO accountsDTO) throws Exception;

    BodyResponse<Object> findByEmail(String email) throws Exception;

    BodyResponse<Object> update(UserDTO userDTO) throws Exception;

    BodyResponse<Object> delete(AccountsDTO accountsDTO) throws Exception;

}