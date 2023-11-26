package com.ryo.elrys.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.payload.BodyResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface ResponseService {

    BodyResponse<Object> register(AccountsModel accountsModel) throws JsonProcessingException;
    BodyResponse<Object> login(AccountsModel accountsModel, HttpServletRequest requestHeader) throws Exception;
    BodyResponse<Object> getInfo(String email) throws Exception;
    BodyResponse<Object> update(DataModel dataModel) throws Exception;
    BodyResponse<Object> delete(AccountsModel accountsModel) throws Exception;
    BodyResponse<Object> changePass(String email, String oldPassword, String newPassword) throws Exception;

}