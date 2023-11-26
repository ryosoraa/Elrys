package com.ryo.elrys.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.payload.DataResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {

    // REGISTER
    BodyResponse<DataResponse> register(AccountsModel accountsModel) throws Exception;

    // LOGIN
    BodyResponse<DataResponse> login(AccountsModel accountsModel, HttpServletRequest requestHeader) throws Exception;

    // Find By Email
    BodyResponse<JsonNode> getInfo(String email) throws Exception;

    // Update
    Object update(DataModel dataModel) throws Exception;

    // Delete
    BodyResponse<JsonNode> delete(AccountsModel accountsModel) throws Exception;

    Object changePassword(String email, String oldPassword, String newPassword) throws Exception;
}
