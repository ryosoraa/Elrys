package com.ryo.elrys.service.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.payload.DataResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    BodyResponse<DataResponse> register(AccountsModel accountsModel) throws Exception;
    BodyResponse<DataResponse> login(AccountsModel accountsModel, HttpServletRequest requestHeader) throws Exception;
    BodyResponse<JsonNode> getInfo(String email) throws Exception;
    BodyResponse<JsonNode> update(DataModel dataModel) throws Exception;
    BodyResponse<JsonNode> delete(AccountsModel accountsModel) throws Exception;
    BodyResponse<JsonNode> changePassword(String email, String oldPassword, String newPassword) throws Exception;
}
