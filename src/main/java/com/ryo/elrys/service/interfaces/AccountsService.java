package com.ryo.elrys.service.interfaces;

import com.ryo.elrys.model.DTO.LoginDTO;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.response.BodyResponse;

public interface AccountsService {

    BodyResponse<Object> register(RegisterDTO registerDTO) throws Exception;
    BodyResponse<Object> login(LoginDTO LoginDTO) throws Exception;
    BodyResponse<Object> findByEmail(String email) throws Exception;
    BodyResponse<Object> update(RegisterDTO registerDTO) throws Exception;
    BodyResponse<Object> delete(String email) throws Exception;
    BodyResponse<Object> findLogs(String email) throws Exception;

}