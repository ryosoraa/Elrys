package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.exceptions.UserNotFoundException;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.payload.DataResponse;
import com.ryo.elrys.service.interfaces.UserService;
import com.ryo.elrys.service.interfaces.ResponseService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseServiceImpl implements ResponseService {

    @Autowired
    UserService userService;

    @Override
    public BodyResponse<Object> register(AccountsModel accountsModel) throws Exception{
        Object response = userService.register(accountsModel);

        if (response.equals("UserController already exists")) {
            throw new UserNotFoundException("User Not");
        }

        return BodyResponse.builder()
                .status("Success")
                .data(new DataResponse(accountsModel).registerResponse())
                .message("Successful Registration")
                .build();
    }

    @Override
    public BodyResponse<Object> login(AccountsModel accountsModel, HttpServletRequest requestHeader) throws Exception {
        Object loginResponse = userService.login(accountsModel, requestHeader);

        if (loginResponse.equals("User not found")) {
            return BodyResponse.builder()
                    .status("Failed")
                    .message("User not found")
                    .build();
        }

        return BodyResponse.builder()
                .status("Success")
                .data(new DataResponse((JsonNode) loginResponse).loginResponse())
                .message("Login successful")
                .build();
    }

    @Override
    public BodyResponse<Object> getInfo(String email) throws Exception {
        Object findResponse = userService.getInfo(email);

        if (findResponse.equals("not found")) {
            return BodyResponse.builder()
                    .status("Failed")
                    .message("UserController not found")
                    .build();
        }
        return BodyResponse.builder()
                .status("Success")
                .data(findResponse)
                .message("Account in Discover")
                .build();
    }

    @Override
    public BodyResponse<Object> update(DataModel dataModel) throws Exception {
        Object findResponse = userService.update(dataModel);
        if (findResponse.equals("User not found")) {
            return BodyResponse.builder()
                    .status("Failed")
                    .message("User not found")
                    .build();
        }
        return BodyResponse.builder()
                .status("Success")
                .data(findResponse)
                .message("Update Success")
                .build();
    }

    @Override
    public BodyResponse<Object> delete(AccountsModel accountsModel) throws Exception {
        Object findResponse = userService.delete(accountsModel);
        if (findResponse.equals("User Not Found")) {
            return BodyResponse.builder()
                    .status("Failed")
                    .message("User Not Found")
                    .build();
        }
        return BodyResponse.builder()
                .status("Success")
                .data(findResponse)
                .message("Delete Success")
                .build();
    }

    @Override
    public BodyResponse<Object> changePass(String email, String oldPassword, String newPassword) throws Exception {
        Object findResponse = userService.changePassword(email, oldPassword, newPassword);
        if (findResponse.equals("User Not Found")) {
            return BodyResponse.builder()
                    .status("Failed")
                    .message("User Not Found")
                    .build();
        }
        return BodyResponse.builder()
                .status("Success")
                .data(findResponse)
                .message("Change Success")
                .build();
    }



}
