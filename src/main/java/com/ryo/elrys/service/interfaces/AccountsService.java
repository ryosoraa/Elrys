package com.ryo.elrys.service.interfaces;

import com.ryo.elrys.model.DTO.LoginDTO;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.response.BodyResponse;

public interface AccountsService {

    BodyResponse<Object> register(RegisterDTO registerDTO) throws Exception;

    BodyResponse<Object> login(LoginDTO LoginDTO) throws Exception;

}