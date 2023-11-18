package com.ryo.elrys.service.interfaces;

import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.model.DTO.DeleteDTO;
import com.ryo.elrys.model.DTO.LoginDTO;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.response.BodyResponse;

public interface AccountsService {

    BodyResponse<Object> register(AccountsDTO accountsDTO) throws Exception;

    BodyResponse<Object> login(LoginDTO LoginDTO) throws Exception;

    BodyResponse<Object> findByEmail(String email) throws Exception;

    BodyResponse<Object> update(RegisterDTO registerDTO) throws Exception;

    BodyResponse<Object> delete(DeleteDTO deleteDTO) throws Exception;

}